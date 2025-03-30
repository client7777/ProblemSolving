import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static ArrayList<Integer> startPosition = new ArrayList<>();
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<int[]>[] adjList;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); //kist  기사단 팀원의 수
        int v = Integer.parseInt(st.nextToken()); // 장소의 수
        int e = Integer.parseInt(st.nextToken()); // 도로의 수

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); //kist의 위치
        int b = Integer.parseInt(st.nextToken()); //씨알푸드의 위치

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            startPosition.add(Integer.parseInt(st.nextToken()));
        }
        
        adjList = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            adjList[from].add(new int[]{to,l});
            adjList[to].add(new int[]{from,l});
        }

        int[] dist1 = dijkstra(v, a);
        int[] dist2 = dijkstra(v, b);

        int ans = 0;

        for(int start:startPosition)
        {
            int dist_a = dist1[start] == INF ? -1 : dist1[start];
            int dist_b = dist2[start] == INF ? -1 : dist2[start];

            ans += (dist_a + dist_b);
        }

        System.out.print(ans);
    }

    static int[] dijkstra(int v, int start)
    {
        int[] dist = new int[v+1];
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

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new Edge(nextNode, dist[nextNode]));
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
