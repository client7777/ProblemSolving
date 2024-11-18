package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1916
{
    static int n,m, start, end;
    static ArrayList<int[]>[] adList;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 노드의 개수
        m = Integer.parseInt(br.readLine()); // 간선의 개수

        adList = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); 
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adList[a].add(new int[]{b,w}); // a -> b 방향으로 향하는 노드와 가중치
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()); // 출발노드
        end = Integer.parseInt(st.nextToken()); // 도착노드
        System.out.print(dijk(start));
    }
    static int dijk(int start)
    {
        int[] dist = new int[n+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int nowVertex = cur[0];
            int nowCost = cur[1];

            if(nowCost > dist[nowVertex]) continue;

            for(int[] next:adList[nowVertex])
            {
                int nextNode = next[0];
                int nextCost = next[1];
        
                //dist[nextNode]는 nextNode까지 가는 현재까지 알려진 최단 거리
                //출발지에서 현재 정점을 거쳐서 nextNode까지 가는 거리가 더 짧다면
                if(dist[nextNode] > dist[nowVertex] + nextCost)
                {
                    dist[nextNode] = dist[nowVertex] + nextCost;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist[end];
    }
}
