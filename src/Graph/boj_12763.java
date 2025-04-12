package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_12763
{
    static final int INF = Integer.MAX_VALUE;
    static int n,t,m;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int l = Integer.parseInt(br.readLine());
        for(int i=0; i<l; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int minute = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,minute,cost));
            graph[v].add(new Node(u,minute,cost));
        }

        System.out.print(dijkstra());
    }

    static int dijkstra()
    {

        int[][] d = new int[n+1][t + 1];
        for(int[] row : d) Arrays.fill(row, INF);
        d[1][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curMinute = cur.minute;
            int curCost = cur.cost;

            if(curCost > d[curNode][curMinute]) continue;

            for(Node next:graph[curNode])
            {
                int nextNode = next.node;
                int nextMinute = curMinute + next.minute;
                int nextCost = curCost + next.cost;

                if(nextMinute > t || nextCost > m) continue;

                if(d[nextNode][nextMinute] > nextCost)
                {
                    d[nextNode][nextMinute] = nextCost;
                    pq.add(new Node(nextNode, nextMinute, d[nextNode][nextMinute]));
                }
            }
        }

        int minCost = INF;
        for(int i=0; i<=t; i++)
        {
            minCost = Math.min(minCost, d[n][i]);
        }

        return minCost == INF ? -1 : minCost;
    }

    static class Node implements Comparable<Node>
    {
        int node;
        int minute;
        int cost;

        public Node(int node, int minute, int cost)
        {
            this.node = node;
            this.minute = minute;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o)
        {
            if (this.cost == o.cost) {
                return Integer.compare(this.minute, o.minute); // 비용이 같다면 시간을 기준으로 정렬
            }
            return Integer.compare(this.cost, o.cost); // 비용 기준 정렬
        }
    }
}
