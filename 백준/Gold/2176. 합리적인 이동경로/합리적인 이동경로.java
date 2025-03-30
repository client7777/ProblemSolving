import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static ArrayList<int[]>[] adjList;
    static int[] dist;
    static int[] d;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new int[]{b,c});
            adjList[b].add(new int[]{a,c});
        }

        dist = dijkstra(); // t에서 모든 정점까지 최단 거리

        d = new int[n+1];
        Arrays.fill(d, -1);
        d[2] = 1; // t에서 t로 가는 경로는 1개

        System.out.print(countPath(1));

    }

    static int countPath(int start)
    {
        if(d[start] != -1) return d[start];

        d[start] = 0;

        for(int[] next:adjList[start])
        {
            int nextNode = next[0];

            if(dist[nextNode] < dist[start])
            {
                d[start] += countPath(nextNode);
            }
        }
        return d[start];
    }

    static int[] dijkstra()
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[2] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(2,0));

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
        public int compareTo(Edge o)
        {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
