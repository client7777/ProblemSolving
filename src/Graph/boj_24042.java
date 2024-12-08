package Graph;

import java.io.*;
import java.util.*;

public class boj_24042
{
    static final long INF = Long.MAX_VALUE;
    static int n,m;
    static long[] dist;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new long[n+1];
        Arrays.fill(dist, INF);

        graph = new ArrayList[n+1];
        for(int i=0; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,i});
            graph[v].add(new int[]{u,i});
        }

        dijkstra();
    }
    static void dijkstra()
    {
        dist[1] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{1,0});

        while(!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                long nextDist;

                if(curDist <= next[1]) nextDist = next[1] + 1;
                else
                {
                    nextDist = ((long) Math.ceil(((double)curDist - next[1])/m)) * m + next[1] + 1;
                }
                if(dist[nextNode] > nextDist)
                {
                    dist[nextNode] = nextDist;
                    pq.add(new long[]{nextNode, nextDist});
                }
            }
        }
        System.out.print(dist[n]);
    }
}
