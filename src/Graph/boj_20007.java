package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_20007
{
    static final int INF = Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken()); // 왕복 거리 제한
        int y = Integer.parseInt(st.nextToken()); // 시작 노드

        graph = new ArrayList[n];
        for(int i=0; i<n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b,c});
            graph[b].add(new int[]{a,c});
        }

        dist = new int[n]; // 집에서 각 노드까지의 최단 거리를 저장
        dijkstra(y);

        Arrays.sort(dist); // 가까운 집부터 방문

        ArrayList<Integer> fullDist = new ArrayList<>();
        for(int val:dist)
        {
            if(2  * val > x)
            {
                //하나의 노드라도 왕복 거리가 x를 초과한다면 모든 노드를 방문하는 것은 불가능하므로
                //-1을 출력하고 프로그램 종료
                System.out.print(-1);
                return;
            }

            fullDist.add(2 * val);
        }

        int day = 1;
        int sum = 0;
        for(int val:fullDist)
        {
            if(sum + val <= x)
            {
                sum += val;
            }
            else
            {
                day++;
                sum = val;
            }
        }

        System.out.print(day);
    }

    static void dijkstra(int start)
    {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
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
}
