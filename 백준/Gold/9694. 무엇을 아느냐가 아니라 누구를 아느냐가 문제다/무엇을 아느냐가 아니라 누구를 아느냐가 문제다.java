import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 간선의 개수
            int m = Integer.parseInt(st.nextToken()); // 노드의 개수
            
            ArrayList<int[]>[] adjList = new ArrayList[m];
            for(int i=0; i<m; i++)
            {
                adjList[i] = new ArrayList<>();
            }

            for(int i=0; i<n; i++)
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                adjList[x].add(new int[]{y,z});
                adjList[y].add(new int[]{x,z});
            }

            sb.append("Case #").append(t+1).append(": ");
            dijkstra(adjList, m);
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static void dijkstra(ArrayList<int[]>[] graph, int m)
    {
        int[] prevNode = new int[m];
        int[] dist = new int[m];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prevNode, -1);

        dist[0] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0,0));

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int curNode = cur.node;
            int curCost = cur.cost;

            if(curCost > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];

                if(dist[nextNode] > curCost + nextCost)
                {
                    dist[nextNode] = curCost + nextCost;
                    pq.add(new Edge(nextNode, dist[nextNode]));
                    prevNode[nextNode] = curNode;
                }
            }
        }

        if(dist[m-1] == Integer.MAX_VALUE)
        {
            sb.append(-1);
        }
        else
        {
            Stack<Integer> stack = new Stack<>();
            int cur = m-1;

            while (cur != -1)
            {
                stack.add(cur);
                cur = prevNode[cur];
            }

            while (!stack.isEmpty())
            {
                sb.append(stack.pop()).append(" ");
            }
        }
    }

    static class Edge implements Comparable<Edge>
    {
        int node;
        int cost;

        public Edge(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
