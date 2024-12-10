package Graph;

import java.io.*;
import java.util.*;

public class boj_14284
{
    static int n,m,s,t;
    static ArrayList<int[]>[] graph;
    static int[] dist;
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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 시작 노드
        t = Integer.parseInt(st.nextToken()); // 목적지 노드

        dist = new int[n+1];
        Arrays.fill(dist, INF);

        dijkstra();
    }
    static void dijkstra()
    {
        dist[s] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new int[]{s,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(curNode == t)
            {
                System.out.print(dist[curNode]);
                return;
            }
            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }
}
