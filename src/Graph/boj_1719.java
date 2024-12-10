package Graph;

import java.io.*;
import java.util.*;

public class boj_1719
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static StringBuilder sb = new StringBuilder();
    static int INF = 10000 * 1000;
    static int[] dist;
    static int[][] ans;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});
        }

        dist = new int[n+1];
        ans = new int[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            dijkstra(i);
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) sb.append("- "+ " ");
                else sb.append(ans[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static void dijkstra(int start)
    {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new int[]{start,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                    ans[nextNode][start] = curNode;
                }
            }
        }
    }
}
/*
    //오버플로우 방지를 위해 2로 나눔
    static int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n + 1][n + 1];
        int[][] path = new int[n + 1][n + 1];


        for (int i = 1; i <= n; i++)
        {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }


        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            dist[u][v] = d;
            dist[v][u] = d;
            path[u][v] = v;
            path[v][u] = u;
        }


        for (int k = 1; k <= n; k++)
        {
            for (int i = 1; i <= n; i++)
            {
                for (int j = 1; j <= n; j++)
                {
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (i == j) sb.append("- ");
                else sb.append(path[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
*/
