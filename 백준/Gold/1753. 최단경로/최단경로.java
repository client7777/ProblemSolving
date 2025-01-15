import java.io.*;
import java.util.*;

public class Main
{
    static int v, e, k;
    static boolean[] visit;
    static ArrayList<int[]>[] adList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // 정점의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수
        k = Integer.parseInt(br.readLine()); // 시작 정점의 번호

        adList = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            // u -> v 방향으로 향하는 노드와 그 노드에 대한 가중치를 배열에 저장
            adList[u].add(new int[]{v,w});
        }

        dijkstra(k);
        System.out.print(sb);
    }

    //다익스트라 알고리즘
    static void dijkstra(int start)
    {
        int[] dist = new int[v+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a ->a[1]));
        pq.add(new int[]{start, 0});
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int nowVertex = cur[0]; // 현재 정점
            int nowCost = cur[1];
            
            if(nowCost > dist[nowVertex]) continue;
            
            for(int[] next:adList[nowVertex])
            {
                int nextNode = next[0];
                int nextCost = next[1];
                //더 짧은 경로가 발견되면
                if(dist[nextNode] > dist[nowVertex] + nextCost)
                {
                    dist[nextNode] = dist[nowVertex] + nextCost;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        for(int i=1; i<=v; i++)
        {
            if(dist[i] == INF) sb.append("INF").append('\n');
            else sb.append(dist[i]).append('\n');
        }
    }
}
