import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static ArrayList<Node>[] graph;
    static long[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dist = new long[n+1];
        Arrays.fill(dist, -1);

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1; i<=n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        bfs();

        long max = Long.MIN_VALUE;

        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, dist[i]);
        }

        System.out.print(max);
    }

    static void bfs()
    {
        dist[1] = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curNode = cur.node;
            long curDist = cur.dist;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                long nextDist = next.dist;

                if(dist[nextNode] == -1)
                {
                    dist[nextNode] = curDist + nextDist;
                    q.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }
    }

    static class Node
    {
        int node;
        long dist;

        public Node(int node, long dist)
        {
            this.node = node;
            this.dist = dist;
        }
    }
}
