package Graph;

import java.io.*;
import java.util.*;

public class boj_23801
{
    static int n,m,x,z,p;
    static ArrayList<int[]>[] adjList;
    static ArrayList<Integer> station = new ArrayList<>();
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,w});
            adjList[v].add(new int[]{u,w});
        }
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()); // 출발 정점
        z = Integer.parseInt(st.nextToken()); // 도착 정점

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<p; i++)
        {
            int stop = Integer.parseInt(st.nextToken());
            station.add(stop);
        }

        long[] distFromX = dijkstra(x);
        long[] distFromZ = dijkstra(z);

        long ans = INF;
        for(int node:station)
        {
            if(distFromX[node] != INF && distFromZ[node] != INF)
            {
                ans = Math.min(ans, distFromX[node] + distFromZ[node]);
            }
        }
        System.out.print(ans == INF ? -1 : ans);
    }
    static long[] dijkstra(int start)
    {
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{start,0});
        dist[start] = 0;

        while (!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curWeight = cur[1];
            if(curWeight > dist[curNode]) continue;
            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                long nextWeight = next[1];
                if(dist[nextNode] > dist[curNode] + nextWeight)
                {
                    dist[nextNode] = dist[curNode] + nextWeight;
                    pq.add(new long[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist;
    }
}
