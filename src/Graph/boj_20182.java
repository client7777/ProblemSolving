package Graph;

import java.io.*;
import java.util.*;

public class boj_20182
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

        int maxCost = 0;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            maxCost = Math.max(maxCost, cost);
            graph[u].add(new Node(v,cost));
            graph[v].add(new Node(u,cost));
        }

        int left = 1;
        int right = maxCost;
        int ans = -1;

        while (left <= right)
        {
            int mid = (left + right) / 2;

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

    static boolean dijkstra(int mid)
    {
        int[] cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[startNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curCost = cur.cost;

            if(curCost > cost[curNode]) continue;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                int nextCost = next.cost;

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
        int cost;

        public Node(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}