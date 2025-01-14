import java.io.*;
import java.util.*;

public class Main
{
    static int n,m,k;
    static ArrayList<int[]>[] graph;
    static int[] dist;
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
        pq.add(new int[]{1, 0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = curDist;

                if(next[1] > mid)
                {
                    nextDist++;
                }

                if(nextDist < dist[nextNode])
                {
                    dist[nextNode] = nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist[n] <= k;
    }
}
