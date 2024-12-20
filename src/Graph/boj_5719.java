package Graph;
// 간선 제거
import java.io.*;
import java.util.*;

public class boj_5719
{
    static int n,m,s,d;
    static ArrayList<int[]>[] graph, rev_graph;
    static int INF = 500 * 10000 + 1;
    static int[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n];
            rev_graph = new ArrayList[n];
            for(int i=0; i<n; i++)
            {
                graph[i] = new ArrayList<>();
                rev_graph[i] = new ArrayList<>();
            }

            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph[u].add(new int[]{v,p});
                rev_graph[v].add(new int[]{u,p});
            }

            dist = new int[n];

            dijkstra();
            trace();
            dijkstra();

            if(dist[d] == INF) dist[d] = -1;
            sb.append(dist[d]).append('\n');
        }
        System.out.print(sb);
    }
    static void dijkstra()
    {
        Arrays.fill(dist, INF);
        dist[s] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{s,0});

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
    }
    static void trace()
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(d);

        boolean[][] remove = new boolean[n][n];

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int[] prev:rev_graph[cur])
            {
                int prevNode = prev[0];
                int prevDist = prev[1];

                if(prevDist + dist[prevNode] != dist[cur] || remove[prevNode][cur]) continue;

                q.add(prevNode);
                remove[prevNode][cur] = true;
                graph[prevNode].removeIf(edge -> edge[0] == cur && edge[1] == prevDist);
            }
        }
    }
}
