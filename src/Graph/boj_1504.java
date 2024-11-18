package Graph;

import java.io.*;
import java.util.*;

public class boj_1504
{
    static int n,e;
    static ArrayList<int[]>[] adjList;
    static final int INF = 200_000_000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,c});
            adjList[v].add(new int[]{u,c});
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        //v1,v2를 반드시 거치면서 n번 노드로 이동하려면 1,v1,v2,n번 노드를 제외한 다른 노드를 거치지 않으면 됨
        //1->v1->v2->n or 1->v2->v1->n
        int dist1 = dijkstra(1,v1) + dijkstra(v1,v2) + dijkstra(v2,n);
        int dist2 = dijkstra(1,v2) + dijkstra(v2,v1) + dijkstra(v1,n);

        // 두 경로 모두 n번 노드까지 가는 경로가 존재하지 않는다면 -1, 경로가 존재한다면 최소값 출력
        System.out.print(dist1 >= INF && dist2 >= INF ? -1 : Math.min(dist1, dist2));
    }
    static int dijkstra(int start,int end)
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start,0});
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            if(curCost > dist[curNode]) continue;
            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];
                if(dist[nextNode] > dist[curNode] + nextCost)
                {
                    dist[nextNode] = dist[curNode] + nextCost;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist[end];
    }
}
