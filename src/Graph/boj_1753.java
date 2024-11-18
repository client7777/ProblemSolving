package Graph;

import java.io.*;
import java.util.*;

public class boj_1753
{
    static int v,e,k;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<int[]>[] adjList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        adjList = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,w});
        }
        dijkstra();
    }
    //다익스트라 알고리즘
    static void dijkstra()
    {
        int[] dist = new int[v+1];
        Arrays.fill(dist, INF);
        dist[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{k,0});
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            //큐에서 꺼낸 정점이 이미 더 작은 비용으로 처리된 적이 있다면, 현재 비용이 더 크므로 최단 경로가 될 수 없다.
            if(curCost > dist[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];

                if(dist[nextNode] > dist[curNode] + nextCost)
                {
                    dist[nextNode] = dist[curNode] + nextCost;
                    //다음 인접 노드의 번호와 누적된 거리를 큐에 저장
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        for(int i=1; i<=v; i++)
        {
            sb.append(dist[i] == INF ? "INF" : dist[i]).append('\n');
        }
        System.out.print(sb);
    }
}
