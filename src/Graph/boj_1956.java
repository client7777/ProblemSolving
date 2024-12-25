package Graph;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1956
{
    static int v,e;
    static int INF = 400 * 10000 + 1;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int[][] graph = new int[v+1][v+1];
        for(int i=1; i<=v; i++)
        {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u][v] = d;
        }

        for(int k=1; k<=v; k++)
        {
            for(int i=1; i<=v; i++)
            {
                for(int j=1; j<=v; j++)
                {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int ans = INF;
        for(int i=1; i<=v; i++)
        {
            for(int j=1; j<=v; j++)
            {
                if(i == j) continue;
                ans = Math.min(ans, graph[i][j] + graph[j][i]);
            }
        }
        System.out.print(ans == INF ? -1 : ans);
    }
}
