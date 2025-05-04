import java.io.*;
import java.util.*;

public class Main
{
    static final int INF = 987654321;
    static int n;
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

        String[] states = new String[n+1];
        
        for(int i=1; i<=n; i++)
        {
            String state = br.readLine();
            // i번 노드의 상태
            states[i] = state;
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=i+1; j<=n; j++)
            {
                int cost = calCost(states[i], states[j]);

                graph[i].add(new Node(j, cost));
                graph[j].add(new Node(i, cost));
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        System.out.print(dijkstra(startNode, endNode));
    }

    static int dijkstra(int start, int end)
    {
        int[] cost = new int[n+1];
        Arrays.fill(cost ,INF);
        cost[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

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

                if(cost[nextNode] > curCost + nextCost)
                {
                    cost[nextNode] = curCost + nextCost;
                    pq.add(new Node(nextNode, cost[nextNode]));
                }
            }
        }

        return cost[end];
    }

    static int calCost(String str1, String str2)
    {
        int cost = 0;

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        for(int i=0; i< ch1.length; i++)
        {
            // char 타입은 산술 연산 시 int로 자동 형변환된다
            // 문자 연산은 내부적으로 유니코드 정수값(int)으로 처리된다
            cost  += (ch1[i] - ch2[i]) * (ch1[i] - ch2[i]);
        }

        return cost;
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
