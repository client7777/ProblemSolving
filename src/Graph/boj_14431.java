package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_14431
{
    static ArrayList<Node>[] graph;
    static final int MAXVALUE = 10000;
    static boolean[] isPrime = new boolean[MAXVALUE + 1];
    static int startX, startY, endX, endY;
    static int n;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        n = Integer.parseInt(br.readLine());

        Pos[] pos = new Pos[n+2];
        pos[0] = new Pos(startX, startY);
        pos[n+1] = new Pos(endX, endY);

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(x,y);
        }

        graph = new ArrayList[n+2];
        for(int i=0; i<n+2; i++)
        {
            graph[i] = new ArrayList<>();
        }

        checkPrime();

        for(int i=0; i<n+2; i++)
        {
            for(int j=i+1; j<n+2; j++)
            {
                int x1 = pos[i].x;
                int y1 = pos[i].y;
                int x2 = pos[j].x;
                int y2 = pos[j].y;

                int dist = calDist(x1,y1,x2,y2);

                if(isPrime[dist])
                {
                    graph[i].add(new Node(j,dist));
                    graph[j].add(new Node(i,dist));
                }
            }
        }

        System.out.print(dijkstra());
    }

    static int dijkstra()
    {
        int[] dist = new int[n+2];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(Node next:graph[curNode])
            {
                int nextNode = next.node;
                int nextDist = next.dist;

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        return dist[n+1] == Integer.MAX_VALUE ? -1 : dist[n+1];
    }

    static int calDist(int x1, int y1, int x2 ,int y2)
    {
        return (int)Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2 - y1,2));
    }

    static void checkPrime()
    {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int i=2; i<=Math.sqrt(MAXVALUE); i++)
        {
            if(isPrime[i])
            {
                for(int j=i*i; j<=MAXVALUE; j+=i)
                {
                    isPrime[j] = false;
                }
            }
        }
    }

    static class Node implements Comparable<Node>
    {
        int node;
        int dist;

        public Node(int node, int dist)
        {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o)
        {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static class Pos
    {
        int x;
        int y;

        public Pos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
