import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n,m;
    static int a,b,c;
    static long[] cost;
    static ArrayList<Node>[] graph;
    static long answer = -1;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

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

            maxCost = Math.max(maxCost,cost);

            graph[u].add(new Node(v,cost));
            graph[v].add(new Node(u,cost));
        }

        long left = 1;
        long right = maxCost;

        while (left <=right)
        {
            long mid = (left + right) / 2;

            if(dijkstra(mid))
            {
                answer = mid;
                right = mid -1;
            }
            else
                left = mid + 1;
        }

        System.out.print(answer);
    }
    static boolean dijkstra(long max)
    {
        cost = new long[n+1];
        Arrays.fill(cost, Long.MAX_VALUE);
        cost[a] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(a,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            long curCost = cur.cost;

            if(curCost > cost[curNode]) continue;

            for(Node next:graph[curNode])
            {
                int nextNode = next.node;
                long nextCost = next.cost;

                if(nextCost > max) continue;

                if(cost[nextNode] > curCost + nextCost)
                {
                    cost[nextNode] = curCost + nextCost;
                    pq.add(new Node(nextNode, cost[nextNode]));
                }
            }
        }
        return cost[b] <= c;
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
