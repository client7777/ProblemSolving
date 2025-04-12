package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_11952
{
    static int n,m,k,s;
    static int p,q;
    static boolean[] zombieNode, dangerCity;
    static long[] distFromZombie;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> zombieQ = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        distFromZombie = new long[n+1];
        Arrays.fill(distFromZombie, -1);

        zombieNode = new boolean[n+1]; //좀비 노드 표시
        dangerCity = new boolean[n+1]; //위험 도시
        for(int i=0; i<k; i++)
        {
            int zombie = Integer.parseInt(br.readLine());
            zombieNode[zombie] = true;
            zombieQ.add(zombie);
            distFromZombie[zombie] = 0;
        }

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        bfs();
        for(int i=1; i<=n; i++)
        {
            //좀비 노드에서 도달 가능하고 거리가 s 이하인 노드는 위험한 도시
            if(distFromZombie[i] != -1 && distFromZombie[i] <= s) dangerCity[i] = true;
        }

        System.out.print(dijkstra());
    }

    static void bfs()
    {
        while (!zombieQ.isEmpty())
        {
            int cur = zombieQ.poll();

            for(int next:graph[cur])
            {
                if(distFromZombie[next] != -1) continue;
                distFromZombie[next] = distFromZombie[cur] + 1;
                zombieQ.add(next);
            }
        }
    }

    static long dijkstra()
    {
        long[] totalCost = new long[n+1];
        Arrays.fill(totalCost, Long.MAX_VALUE);
        totalCost[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            long curCost = cur.cost;

            if(curCost > totalCost[curNode]) continue;

            for(int next:graph[curNode])
            {
                if(zombieNode[next]) continue;

                long nextCost = curCost;

                if(next != n)
                {
                    nextCost += (dangerCity[next] ? q : p);
                }

                if(totalCost[next] > nextCost)
                {
                    totalCost[next] = nextCost;
                    pq.add(new Node(next, totalCost[next]));
                }
            }
        }

        return totalCost[n];
    }

    static class Node implements Comparable<Node>
    {
        int node;
        long cost;

        public Node(int node, long cost)
        {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o)
        {
            return Long.compare(this.cost, o.cost);
        }
    }
}
