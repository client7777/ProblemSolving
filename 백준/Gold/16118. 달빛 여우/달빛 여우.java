import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

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
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b,d * 2});
            graph[b].add(new int[]{a,d * 2});
        }

        int[] fox = dijkstra_fox(1);
        int[][] wolf = dijkstra_wolf(1);

        int cnt = 0;
        for(int i=1; i<=n; i++)
        {
            if(fox[i] < Math.min(wolf[i][0], wolf[i][1])) cnt++;
        }
        System.out.print(cnt);

    }
    static int[] dijkstra_fox(int start)
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }

        }
        return dist;
    }

    static int[][] dijkstra_wolf(int start)
    {
        int[][] dist = new int[n+1][2];
        for(int[] row:dist)
        {
            Arrays.fill(row, INF);
        }
        dist[start][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0 ,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            int curState = cur[2];

            if(curDist > dist[curNode][curState]) continue;

            for(int[] next:graph[curNode])
            {
                int nextState = 1 - curState;
                int nextNode = next[0];
                int nextDist;

                if(curState == 0)
                {
                    nextDist = curDist + next[1] / 2;
                }
                else
                    nextDist = curDist + next[1] * 2;

                if(dist[nextNode][nextState] > nextDist)
                {
                    dist[nextNode][nextState] = nextDist;
                    pq.add(new int[]{nextNode, nextDist, nextState});
                }
            }
        }
        return dist;
    }
}
