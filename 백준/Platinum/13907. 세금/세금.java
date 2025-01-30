import java.io.*;
import java.util.*;

public class Main
{
    static int n,m,k;
    static int s,d;
    static int INF = 1000 * 30000;
    static ArrayList<int[]>[] graph;
    static int[] tax;
    static int[][] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // dist[i][j] = i번 정점까지 j개의 정점을 거쳐왔을 때의 최단거리
        dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            Arrays.fill(dist[i], INF);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

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

            graph[a].add(new int[]{b,d});
            graph[b].add(new int[]{a,d});
        }

        tax = new int[k+1];
        for(int i=1; i<=k; i++)
        {
            tax[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        dijkstra();

        for(int i=0; i<=k; i++)
        {
            int ans = INF;
            for(int j=0; j<=n; j++)
            {
                dist[d][j] += tax[i] * j;
                ans = Math.min(ans, dist[d][j]);
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
    static void dijkstra()
    {
        dist[s][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{s,0,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            int curPass = cur[2];

            if(curDist > dist[curNode][curPass] || curPass == n) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode][curPass+1] > dist[curNode][curPass] + nextDist)
                {
                    dist[nextNode][curPass + 1] = dist[curNode][curPass] + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode][curPass + 1], curPass + 1});
                }
            }
        }
    }
}

