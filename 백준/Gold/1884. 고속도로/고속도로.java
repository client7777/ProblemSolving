import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int k, n, r;
    static final int INF = 987654321;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        r = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=0; i<r; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(d,l,t));
        }

        dijkstra();
    }

    static void dijkstra()
    {
        int[][] dist = new int[n+1][k+1]; // dist[i][j] = i번 노드까지 j원을 지불해서 이동한 최단 거리
        for(int i=1; i<=n; i++)
        {
            Arrays.fill(dist[i], INF);
        }

        dist[1][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curLength = cur.length;
            int curCost = cur.cost;

            if(curLength > dist[curNode][curCost]) continue;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                int nextLength = next.length;
                int nextCost = next.cost;

                int totalCost = curCost + nextCost;
                if(totalCost > k) continue; // 현재 가지고 있는 예산을 초과한다면 해당 경로 무시
                
                if(dist[nextNode][totalCost] > curLength + nextLength)
                {
                    dist[nextNode][totalCost] = curLength + nextLength;
                    pq.add(new Node(nextNode, dist[nextNode][totalCost], totalCost));
                }
            }
        }

        int minLength = INF;
        for(int i=0; i<=k; i++)
        {
            minLength = Math.min(minLength, dist[n][i]);
        }

        System.out.print(minLength == INF ? -1 : minLength);
    }

    static class Node implements Comparable<Node>
    {
        int node;
        int length;
        int cost;

        public Node(int node, int length, int cost)
        {
            this.node = node;
            this.length = length;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o)
        {
            return Integer.compare(this.length, o.length);
        }
    }
}
