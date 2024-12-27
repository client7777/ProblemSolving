package Graph;
//노드보다 간선에 초점
import java.io.*;
import java.util.StringTokenizer;

public class boj_13141
{
    static int[][] graph;
    static int INF = 100 * 200;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }

        Edge[] edges = new Edge[m];
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[s][e] = Math.min(graph[s][e], l);
            graph[e][s] = Math.min(graph[e][s], l);
            edges[i] = new Edge(s,e,l);
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        double ans = INF;
        //노드의 개수 범위가 200으로 큰 편이 아니므로 모든 노드에 대해 완전 탐색 수행
        //모든 간선에 불을 한번씩 붙임
        for(int i=1; i<=n; i++)
        {
            double tot = 0;
            for(int j=0; j<m; j++)
            {
                int s = edges[j].start;
                int e = edges[j].end;
                int l = edges[j].length;
                //불을 붙인 노드를 기준으로 간선 s-e가 타는 시간을 구함
                //모든 간선이 불에 타는 시간은 가장 긴 간선이 불에 타는 시간과 동일하다.
                tot = Math.max(tot, (double) (l + graph[i][s] + graph[i][e]) / 2);
            }
            ans = Math.min(ans, tot);
        }
        System.out.print(ans);
    }
    static class Edge
    {
        int start;
        int end;
        int length;

        public Edge(int start, int end, int length)
        {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
}
