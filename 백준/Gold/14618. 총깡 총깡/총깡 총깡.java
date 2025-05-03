import java.io.*;
import java.util.*;

public class Main
{
    static final int INF = 987654321;
    static int n,m;
    static int[] typeA, typeB;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        typeA = new int[n+1];
        typeB = new int[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList();
        }
        
        int j = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        //a형 집
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
        {
            typeA[i] = Integer.parseInt(st.nextToken());
        }

        //b형 집
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
        {
            typeB[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            graph[x].add(new Node(y,z));
            graph[y].add(new Node(x,z));
        }


        int[] dist = dijkstra(j);

        //a형 집에서만 진서의 집에 갈 수 있는 경우 a와 거리를 출력, B형 집에서만 진서의 집에 갈 수 있는 경우 B와 거리를 출력
        //둘 다 진서의 집에 갈 수 없는 경우 -1 출력

        int aMinDist = INF;
        int bMinDist = INF;

        for(int i=0; i<k; i++)
        {
            aMinDist = Math.min(aMinDist, dist[typeA[i]]);
        }

        for(int i=0; i<k; i++)
        {
            bMinDist = Math.min(bMinDist, dist[typeB[i]]);
        }

        if(aMinDist == INF && bMinDist == INF)
        {
            System.out.print(-1);
        }
        else if(aMinDist <= bMinDist)
        {
            System.out.println("A");
            System.out.print(aMinDist);
        }
        else
        {
            System.out.println("B");
            System.out.print(bMinDist);
        }
    }

    static int[] dijkstra(int start)
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                int nextDist = next.dist;

                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        return dist;
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
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
