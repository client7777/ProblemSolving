package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_14554
{
    static final int MOD = 1_000_000_009;
    static final long INF = Long.MAX_VALUE;
    static int n,m,s,e;
    static ArrayList<Node>[] graph;
    static long[] dist, cnt;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        dijkstra();
    }

    static void dijkstra()
    {
        cnt = new long[n+1];
        cnt[s] = 1;

        dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            long curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                long nextDist = next.dist;

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    cnt[nextNode] = cnt[curNode];
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
                else if(dist[nextNode] == dist[curNode] + nextDist)
                {
                    cnt[nextNode] += cnt[curNode];
                    cnt[nextNode] %= MOD;
                }
            }
        }

        System.out.print(cnt[e]);
    }

    static class Node implements Comparable<Node>
    {
        int node;
        long dist;

        public Node(int node, long dist)
        {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}
