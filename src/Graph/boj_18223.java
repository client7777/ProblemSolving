package Graph;

import java.io.*;
import java.util.*;

public class boj_18223
{
    static int v,e,p;
    static ArrayList<int[]>[] graph;
    static int[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken()); // 노드의 개수
        e = Integer.parseInt(st.nextToken()); // 간선의 개수
        p = Integer.parseInt(st.nextToken()); // 건우의 위치

        graph = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[x].add(new int[]{y,d});
            graph[y].add(new int[]{x,d});
        }

        int[] startArr = dijkstra(1);
        int[] gunWoo = dijkstra(p);

        System.out.print(startArr[v] == startArr[p] + gunWoo[v] ? "SAVE HIM" : "GOOD BYE");
    }
    static int[] dijkstra(int start)
    {
        dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
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
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        return dist;
    }
}
