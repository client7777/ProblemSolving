import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 노드의 개수

        //친구 a,b,c의 위치, 친구들은 n개의 땅 중 하나에 사느 것이 보장됨
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        int m = Integer.parseInt(br.readLine()); // 간선의 개수

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            //양방향 간선
            graph[u].add(new Edge(v,d));
            graph[v].add(new Edge(u,d));
        }

        int[] distA = dijkstra(a);
        int[] distB = dijkstra(b);
        int[] distC = dijkstra(c);

        int[] maxDist = new int[n+1];

        for(int i=1; i<=n; i++)
        {
            maxDist[i] = Math.min(distA[i], Math.min(distB[i], distC[i]));
        }

        int maxValue = -1;
        int ans = -1;

        for(int i=1; i<=n; i++)
        {
            if(i == a || i == b || i == c) continue;

            if(maxDist[i] > maxValue)
            {
                ans = i;
                maxValue = maxDist[i];
            }
        }
        System.out.print(ans);
    }

    static int[] dijkstra(int start)
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(Edge next:graph[curNode])
            {
                int nextNode = next.node;
                int nextDist = next.dist;

                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new Edge(nextNode,dist[nextNode]));
                }
            }
        }
        return dist;
    }

    static class Edge implements Comparable<Edge>
    {
        int node;
        int dist;

        public Edge(int node, int dist)
        {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}