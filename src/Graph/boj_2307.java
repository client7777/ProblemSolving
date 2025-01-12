package Graph;

import java.io.*;
import java.util.*;

public class boj_2307
{
    static int n,m;
    static int[] path, dist;
    static ArrayList<int[]>[] graph;
    static int INF = Integer.MAX_VALUE;
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b,t});
            graph[b].add(new int[]{a,t});
        }

        path = new int[n+1];
        dijkstra(1);
        int minTime = dist[n];

        int res = 0;
        int end = n;
        while (true)
        {
            if(end == 1) break;
            int start = path[end];

            dijkstra_delay(1, start, end);
            res = Math.max(res, dist[n]);

            end = start;
        }

        System.out.print(res == INF ? -1 : res - minTime);
    }

    static void dijkstra(int start)
    {
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0});
        
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
                
                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                    path[nextNode] = curNode;
                }
            }
        }
    }

    static void dijkstra_delay(int start, int from , int to)
    {
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0});

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

                //현재 검문중인 간선이라면
                if(curNode == from && nextNode == to) continue;

                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }
}
