import java.io.*;
import java.util.*;

public class Main
{
    static int v,e;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});
        }


        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] mac = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            mac[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[] star = new int[s];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<s; i++)
        {
            star[i] =Integer.parseInt(st.nextToken());
        }

        int[] macdonald = dijkstra(mac);
        int[] starbucks = dijkstra(star);

        int res = Integer.MAX_VALUE;

        for(int i=1; i<=v; i++)
        {
            if(macdonald[i] == 0 || starbucks[i] == 0) continue;
            if(macdonald[i] <= x && starbucks[i] <= y)
            {
                res = Math.min(res, macdonald[i] + starbucks[i]);
            }
        }

        System.out.print(res == Integer.MAX_VALUE ? -1 : res);
    }

    static int[] dijkstra(int[] arr)
    {
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));

        for(int i=0; i<arr.length; i++)
        {
            pq.add(new int[]{arr[i], 0});
            dist[arr[i]] = 0;
        }

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

                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist;
    }
}
