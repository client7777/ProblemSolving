package Graph;
// 다익스트라 + 이분 탐색
import java.io.*;
import java.util.*;

public class boj_20183
{
    static final long INF = Long.MAX_VALUE;
    static int n,m,a,b;
    static long c;
    static ArrayList<int[]>[] graph;
    static long[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());

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
            int fee = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,fee});
            graph[v].add(new int[]{u,fee});
        }
        dist = new long[n+1];

        long start = 1;
        long end = 1_000_000_000;
        long ans = INF;

        while (start <= end)
        {
            long mid = (start + end) / 2;
            if(dijkstra(mid))
            {
                ans = mid;
                end = mid -1;
            }
            else
                start = mid + 1;
        }
        System.out.print(ans == INF ? -1 : ans);
    }
    static boolean dijkstra(long maxFee)
    {
        Arrays.fill(dist, INF);
        dist[a] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{a,0});

        while (!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curCost = cur[1]; // 현재 노드까지의 최소비용을 나타내며 dist[curNode]와 항상 동일함

            if(dist[curNode] < curCost) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];
                if(nextCost > maxFee) continue;
                if(dist[nextNode] > dist[curNode] + nextCost)
                {
                    dist[nextNode] = dist[curNode] + nextCost;
                    pq.add(new long[]{nextNode, dist[curNode] + nextCost});
                }
            }
        }
        return dist[b] <= c;
    }
}
