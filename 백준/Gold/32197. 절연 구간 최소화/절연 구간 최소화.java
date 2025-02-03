import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m,a,b;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

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
            int t = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v,t,0));
            graph[v].add(new Edge(u,t,0));
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        dijkstra();
    }

    static void dijkstra()
    {
        //같은 노드를 방문하더라도 현재 급전 방식(직류/교류)에 따라 비용이 달라질 수 있기 때문에 (노드, 상태)를 함께 고려
        //상태는 직류, 교류 2가지
        int[][] cost = new int[n+1][2];
        for(int i=1; i<=n; i++)
        {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        //출발점에서 갈 수 있는 모든 간선을 큐에 추가
        for(Edge startNode:graph[a])
        {
            pq.add(new Edge(a, startNode.state, 0));
            cost[a][startNode.state] = 0;
        }
        
        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int curNode = cur.node;
            int curState = cur.state;
            int curCost = cur.cost;

            for(Edge next:graph[curNode])
            {
                int nextNode = next.node;
                int nextState = next.state;
                int nextCost = curCost + (curState == nextState ? 0 : 1);

                if(cost[nextNode][nextState] > nextCost)
                {
                    cost[nextNode][nextState] = nextCost;
                    pq.add(new Edge(nextNode, nextState, cost[nextNode][nextState]));
                }
            }
        }
        System.out.print(Math.min(cost[b][0], cost[b][1]));
    }
    static class Edge implements Comparable<Edge>
    {
        int node;
        int state;
        int cost;

        public Edge(int node, int state, int cost) {
            this.node = node;
            this.state = state;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o)
        {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
