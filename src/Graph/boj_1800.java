package Graph;

import java.io.*;
import java.util.*;

public class boj_1800
{
    static int n,m,k;
    static ArrayList<int[]>[] graph;
    static int[] dist; // 무료 회선으로 전환한 수
    static int INF = 1000 * 10000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        int start = 0;
        int end = -1;

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            end = Math.max(end, c);
            graph[u].add(new int[]{v,c});
            graph[v].add(new int[]{u,c});
        }

        int ans = Integer.MIN_VALUE;

        while (start <= end)
        {
            int mid = (start + end) / 2;

            if(dijkstra(mid))
            {
                ans = mid;
                end = mid -1;
            }
            else
                start = mid + 1;
        }

        System.out.print(ans == Integer.MIN_VALUE ? -1 : ans);
    }
    static boolean dijkstra(int mid)
    {
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{1, 0}); // 현재 노드, 무료 회선 전환 횟수

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = curDist; //공짜 회선 개수

                if(next[1] > mid) // 더 비싼 회선가격이 나오면
                {
                    nextDist++; // 공짜 회선으로 전환
                }

                if(nextDist < dist[nextNode])
                {
                    dist[nextNode] = nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist[n] <= k; // 공짜 회선이 k개 보다 적은 개수이면 탐색 가능
    }
}
