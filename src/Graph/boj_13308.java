package Graph;

import java.io.*;
import java.util.*;

public class boj_13308
{
    static int n,m;
    static int[] cost;
    static long[][] dist;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            cost[i] = Integer.parseInt(st.nextToken());
        }

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
            int c = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,c});
            graph[v].add(new int[]{u,c});
        }

        dijkstra();
    }
    static void dijkstra()
    {
        //dist[i][j] = i번 노드에 도달했을 때, 최소 기름값 j를 사용하며 도달하는데 드는 최소 비용
        dist = new long[n+1][2501];
        for(long[] row : dist)
        {
            Arrays.fill(row, Long.MAX_VALUE);
        }
        dist[1][0] = 0; //출발노드에서 시작, 초기 비용은 0

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, cost[1], 0)); // 노드, 최소 기름 값, 총 비용

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();

            if(cur.node == n)
            {
                System.out.print(cur.totalCost);
                return;
            }

            // dist[cur.node][cur.minOil]와 cur.totalCost를 비교해야 함
            if(cur.totalCost > dist[cur.node][cur.minOil]) continue;

            for(int[] next : graph[cur.node])
            {
                int nextNode = next[0];
                int nextCost = next[1];

                // 새로운 경로의 비용 계산
                long newCost = cur.totalCost + (nextCost * cur.minOil);

                // dist[nextNode][새로운 기름 양]에 대해 갱신을 진행
                if(dist[nextNode][cur.minOil] > newCost)
                {
                    dist[nextNode][cur.minOil] = newCost;
                    pq.add(new Edge(nextNode, Math.min(cur.minOil, cost[nextNode]), newCost));
                }
            }
        }
        System.out.print(-1);
    }

    static class Edge implements Comparable<Edge>
    {
        int node, minOil;
        long totalCost;

        public Edge(int node, int minOil, long totalCost)
        {
            this.node = node;
            this.minOil = minOil;
            this.totalCost = totalCost;
        }

        @Override
        public int compareTo(Edge o)
        {
            return Long.compare(this.totalCost, o.totalCost);
        }
    }
}