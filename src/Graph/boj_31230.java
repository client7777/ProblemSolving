package Graph;

import java.io.*;
import java.util.*;

public class boj_31230
{
    static int n,m,a,b;
    static ArrayList<int[]>[] graph;
    static long[] dA, dB;
    static long INF = Long.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

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

        dA = new long[n+1];
        dB = new long[n+1];

        dijkstra(dA, a);
        dijkstra(dB, b);

        chasePath();
        System.out.print(sb);


    }
    static void dijkstra(long[] dist, int start)
    {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{start,0});

        while (!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                long nextDist = next[1];

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new long[]{nextNode, dist[nextNode]});
                }
            }
        }
    }
    static void chasePath()
    {
        int size = 0;
        for(int i=1; i<=n; i++)
        {
            if(dA[i] + dB[i] == dA[b])
            {
                sb.append(i).append(" ");
                size++;
            }
        }
        System.out.println(size);
    }
}
