package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_23286
{
    static int[][] graph;
    static int INF = 300 * 1000000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1]; // i번 노드에서 j번 노드로 가는 길목의 허들 높이 -> graph[i][j]
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            graph[u][v] = h; // 방향 그래프
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    graph[i][j] = Math.min(graph[i][j], Math.max(graph[i][k], graph[k][j]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<t; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(graph[s][e] == INF ? -1 : graph[s][e]).append('\n');
        }
        System.out.print(sb);
    }
}
