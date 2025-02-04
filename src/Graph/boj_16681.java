package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_16681
{
    static int n,m,d,e;
    static long INF = Long.MAX_VALUE;
    static int[] height;
    static ArrayList<Vertex>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 노드
        m = Integer.parseInt(st.nextToken()); // 간선
        d = Integer.parseInt(st.nextToken()); // 체력 소모량
        e = Integer.parseInt(st.nextToken()); // 성취감 획득량


        height = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            height[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long dist = Long.parseLong(st.nextToken());

            graph[from].add(new Vertex(to,dist));
            graph[to].add(new Vertex(from,dist));
        }

        long[] startHome = dijkstra(1);
        long[] startSchool = dijkstra(n);

        long ans = Long.MIN_VALUE;

        for(int i=2; i<n; i++)
        {
            if(startHome[i] != INF && startSchool[i] != INF)
            {
                long result = (long)e * height[i] - (startHome[i] + startSchool[i]) * d;
                ans = Math.max(ans,result);
            }
        }

        System.out.print(ans == Long.MIN_VALUE ? "Impossible" : ans);
    }

    static long[] dijkstra(int start)
    {
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty())
        {
            Vertex cur = pq.poll();
            int curNode = cur.to;
            long curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(Vertex next:graph[curNode])
            {
                int nextNode = next.to;
                long nextDist = next.dist;

                if(height[curNode] < height[nextNode] && dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new Vertex(nextNode, dist[nextNode]));
                }
            }
        }
        return dist;
    }

    static class Vertex implements Comparable<Vertex>
    {
        int to;
        long dist;

        public Vertex(int to, long dist)
        {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o)
        {
            return Long.compare(this.dist, o.dist);
        }
    }
}
