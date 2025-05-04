package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2325
{
    static int n;
    static ArrayList<int[]> destroy = new ArrayList<>();
    static int[] dist;
    static ArrayList<int[]>[] adjList;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            adjList[x].add(new int[]{y,z});
            adjList[y].add(new int[]{x,z});
        }

        //최단 경로상에 존재하는 간선 중 하나를 파괴해서 최단거리의 최댓값
        dist = new int[n+1];

        dijkstra();

        chasePath();

        int max = -1;
        for(int i=0; i<destroy.size(); i++)
        {
            int a = destroy.get(i)[0];
            int b = destroy.get(i)[1];

            int minDist = dijkstra(a,b);
            if(minDist != -1) max = Math.max(max, minDist);
        }

        System.out.print(max);
    }
    static void chasePath()
    {
        boolean[] visit = new boolean[n+1];
        visit[n] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int[] next:adjList[cur])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] + nextDist == dist[cur])
                {
                    destroy.add(new int[]{cur, nextNode});
                    if(!visit[nextNode])
                    {
                        visit[nextNode] = true;
                        q.add(nextNode);
                    }
                }
            }
        }
    }
    static void dijkstra()
    {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist= cur.dist;

            if(curDist > dist[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }
    }

    static int dijkstra(int a,int b)
    {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist= cur.dist;

            if(curDist > dist[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if((curNode == a && nextNode == b) || curNode == b && nextNode == a) continue;

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        return dist[n] == Integer.MAX_VALUE ? -1 : dist[n];
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
