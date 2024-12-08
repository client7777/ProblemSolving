package Graph;
// 1번 노드에서 출발해서 n번 노드에 최소비용으로 도착 -> 다익스트라
import java.io.*;
import java.util.*;

public class boj_5972
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static int[] dist;
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
        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,c});
            graph[v].add(new int[]{u,c});
        }
        dijkstra();
    }
    static void dijkstra()
    {
        dist[1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{1,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if(curCost > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];

                if(dist[nextNode] > dist[curNode] + nextCost)
                {
                    dist[nextNode] = dist[curNode] + nextCost;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }

        System.out.print(dist[n]);
    }
}
