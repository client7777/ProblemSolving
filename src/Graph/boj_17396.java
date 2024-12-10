package Graph;

import java.io.*;
import java.util.*;

public class boj_17396
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static boolean[] visible;
    static long[] dist;
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        for(int i=0; i<n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        visible = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int isVisible = Integer.parseInt(st.nextToken());
            if(isVisible == 0)
            {
                visible[i] = false;
            }
            else if(isVisible == 1)
            {
                visible[i] = true;
            }
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 양방향 연결
            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});
        }
        dist = new long[n];
        Arrays.fill(dist, INF);
        dijkstra(0);
    }
    static void dijkstra(int start)
    {
        dist[start] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{start, 0});

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
                if((!visible[nextNode] || nextNode == n-1) && (dist[nextNode] > dist[curNode] + nextDist))
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new long[]{nextNode, dist[nextNode]});
                }
            }
        }
        System.out.print(dist[n-1] == INF ? -1 : dist[n-1]);
    }
}
