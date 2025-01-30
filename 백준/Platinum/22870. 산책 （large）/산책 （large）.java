import java.io.*;
import java.util.*;

public class Main
{
    static int[] dS,dE;
    static final int INF = Integer.MAX_VALUE;
    static Set<Integer> visitNode = new HashSet<>();
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dS = new int[n+1];
        dE = new int[n+1];

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

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int ans = 0;
        dijkstra(dS, s);
        dijkstra(dE, e);

        ans += dS[e];

        eraseNode(s,e);
        dijkstra(dE,e);

        ans += dE[s];

        System.out.print(ans);

    }
    static void dijkstra(int[] dist, int start)
    {
        Arrays.fill(dist, INF);
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

                if(visitNode.contains(nextNode)) continue;

                if(dist[nextNode] > dist[curNode] + nextDist)
                {
                    dist[nextNode] = dist[curNode] + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
    }
    static void eraseNode(int s, int e)
    {
        int start = s;
        while (true)
        {
            if(start == e) break;

            int min = Integer.MAX_VALUE;
            for(int[] next:graph[start])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(start == nextNode) continue;

                if(dS[start] + nextDist + dE[nextNode] == dS[e])
                {
                    min = Math.min(min, nextNode);
                }
            }
            start = min;
            if(start != e) visitNode.add(start);
        }
    }
}
