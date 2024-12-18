package Graph;
//다익스트라 + 최대 힙
import java.io.*;
import java.util.*;

public class boj_1854
{
    static int n,m,k;
    static ArrayList<int[]>[] graph;
    static PriorityQueue<Integer>[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dist = new PriorityQueue[n+1];
        graph = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Comparator.comparingInt(o->-o));
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b,c});
        }
        dijkstra();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            if(dist[i].size() == k)
            {
                sb.append(dist[i].poll()).append('\n');
            }
            else
                sb.append(-1).append('\n');
        }
        System.out.print(sb);
    }
    static void dijkstra()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{1,0});
        dist[1].add(0);

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                int totalDist = curDist + nextDist;

                if(dist[nextNode].size() < k)
                {
                    dist[nextNode].add(totalDist);
                    pq.add(new int[]{nextNode, totalDist});
                }
                else if(dist[nextNode].peek() > totalDist)
                {
                    dist[nextNode].poll();
                    dist[nextNode].add(totalDist);
                    pq.add(new int[]{nextNode, totalDist});
                }
            }
        }
    }
}
