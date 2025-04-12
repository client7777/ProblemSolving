package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1277
{
    static int n;
    static double INF = Double.MAX_VALUE;
    static double[] dist;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        double m = Double.parseDouble(br.readLine());

        Location[] locations = new Location[n+1];
        for(int i=1; i<=n; i++)
        {
            //i번 발전소의 위치 좌표
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            locations[i] = new Location(x,y);
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;

                int x1 = locations[i].x;
                int y1 = locations[i].y;

                int x2 = locations[j].x;
                int y2 = locations[j].y;

                double dist = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));

                if(dist > m) continue;

                graph[i].add(new Node(j, dist));
            }
        }

        for(int i=0; i<w; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //a번 발전소와 b번 발전소는 연결되어있음
            graph[a].add(new Node(b,0));
            graph[b].add(new Node(a,0));
        }

        dist = new double[n+1];
        dijkstra();
    }

    static void dijkstra()
    {
        Arrays.fill(dist,INF);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            double curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(Node next:graph[curNode])
            {
                int nextNode = next.node;
                double nextDist = next.dist;

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        System.out.print((int)Math.floor(dist[n] * 1000));
    }

    static class Location
    {
        int x;
        int y;

        public Location(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node>
    {
        int node;
        double dist;

        public Node(int node, double dist)
        {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }
}
