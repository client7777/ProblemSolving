import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static int INF = Integer.MAX_VALUE;
    static int[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 노드의 개수
            m = Integer.parseInt(st.nextToken()); // 간선의 개수

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
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new int[]{b,c});
                graph[b].add(new int[]{a,c});
            }

            int k = Integer.parseInt(br.readLine()); // 모임에 참석하는 친구의 수

            ArrayList<Integer> person = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<k; i++)
            {
                person.add(Integer.parseInt(st.nextToken()));
            }

            int[] totalDist = new int[n+1];

            for (Integer pos : person)
            {
                dist = new int[n + 1];
                Arrays.fill(dist, INF);

                dijkstra(pos);

                for (int j = 1; j <= n; j++)
                {
                    totalDist[j] += dist[j];
                }
            }
            int min = INF;
            int idx = -1;

            for(int i=1; i<=n; i++)
            {
                if(totalDist[i] < min || (totalDist[i] == min && i < idx))
                {
                    min = totalDist[i];
                    idx = i;
                }
            }
            sb.append(idx).append('\n');
        }
        System.out.print(sb);
    }
    static void dijkstra(int start)
    {
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start,0});

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
                    pq.add(new int[]{nextNode, nextDist});
                }
            }
        }
    }
}
