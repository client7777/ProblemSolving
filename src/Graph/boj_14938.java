package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_14938
{
    static int n,m,r;
    static int[] item;
    static int[][] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        item = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            item[i] = Integer.parseInt(st.nextToken());
        }

        graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i != j)
                {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for(int i=0; i<r; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.min(graph[u][v],w);
            graph[v][u] = Math.min(graph[v][u],w);
        }
        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE)
                    {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        int max = 0;
        for(int i=1; i<=n; i++)
        {
            int collectItem = 0;
            for(int j=1; j<=n; j++)
            {
                if(graph[i][j] != Integer.MAX_VALUE && graph[i][j] <= m)
                {
                    collectItem += item[j];
                }
                max = Math.max(max, collectItem);
            }
        }
        System.out.print(max);
    }
}
