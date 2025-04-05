import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static int startNode, endNode, money;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        long maxCost = 0;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long cost = Integer.parseInt(st.nextToken());

            maxCost = Math.max(maxCost, cost);
            graph[u].add(new Node(v,cost));
            graph[v].add(new Node(u,cost));
        }

        long left = 0;
        long right = maxCost;
        long ans = -1;

        while (left <= right)
        {
            long mid = (left + right) / 2;

            if(dijkstra(mid))
            {
                ans = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }

        System.out.print(ans);
    }

    static boolean dijkstra(long mid)
    {
        long[] cost = new long[n+1];
        Arrays.fill(cost, Long.MAX_VALUE);
        cost[startNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            long curCost = cur.cost;

            if(curCost > cost[curNode]) continue;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                long nextCost = next.cost;

                if(nextCost > mid) continue;

                if(cost[nextNode] > nextCost + curCost)
                {
                    cost[nextNode] = nextCost + curCost;
                    pq.add(new Node(nextNode, cost[nextNode]));
                }
            }
        }

        return cost[endNode] <= money;
    }

    static class Node implements Comparable<Node>
    {
        int node;
        long cost;

        public Node(int node, long cost)
        {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
