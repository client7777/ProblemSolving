package Graph;

import java.io.*;
import java.util.*;

public class boj_2211
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static int MAX = 100001;
    static int[] dist, path;
    static StringBuilder sb = new StringBuilder();
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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});
        }

        dist = new int[n+1];
        Arrays.fill(dist, MAX);
        dijkstra(1);

        int cnt = 0;
        for(int i=2; i<=n; i++)
        {
            if(path[i] == i) continue; // 복구가 안된 노드는 배제
            cnt++;
            sb.append(i).append(" ").append(path[i]).append('\n');
        }
        System.out.println(cnt);
        System.out.print(sb);
    }
    static void dijkstra(int start)
    {
        path = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            path[i] = i;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0});
        dist[start] = 0;

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
                    path[nextNode] = curNode;
                }
            }
        }
    }
}
