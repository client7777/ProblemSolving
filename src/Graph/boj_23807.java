package Graph;

import java.io.*;
import java.util.*;

public class boj_23807
{
    static int n,m;
    static long ans;
    static final long INF = Long.MAX_VALUE;
    static int x,z,p;
    static long[] distFromX, distFromZ;
    static ArrayList<int[]>[] adjList;
    static ArrayList<Integer> station = new ArrayList<>();
    static ArrayList<Integer> chose = new ArrayList<>();
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
        st = new StringTokenizer(br.readLine()  );
        x = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        p = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<p; i++)
        {
            int stop = Integer.parseInt(st.nextToken());
            station.add(stop);
        }

        distFromX = dijkstra(x);
        distFromZ = dijkstra(z);
        ans = INF;
        backTrack(0,0);
        System.out.print(ans == INF ? -1 : ans);
    }
    static void backTrack(int depth, int at)
    {
        if(depth == 3)
        {
            long sum = distFromX[chose.get(0)] + distFromZ[chose.get(1)] + distFromZ[chose.get(2)];
            ans = Math.min(ans, sum);
        }
        for(int i=at; i<station.size(); i++)
        {
            if(!chose.contains(i))
            {
                chose.add(station.get(i));
                backTrack(depth + 1, i+1);
                chose.remove(chose.size() - 1);
            }
        }
    }
    static long[] dijkstra(int start)
    {
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{start, 0});

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
// 양방향 도로, 출발 정점 x 에서 적어도 3개의 정점을 반드시 거친 후 도착 정점 z에 도달하는 최단 거리
