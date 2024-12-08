package Graph;

import java.io.*;
import java.util.*;

public class boj_9370
{
    static int n,m,t;
    static int s,g,h;
    static ArrayList<int[]>[] graph;
    static final int INF = 50000 * 1000;
    static int[] candidate, dist;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer> answerNode = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int l=0; l<test_case; l++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int j=1; j<=n; j++)
            {
                graph[j] = new ArrayList<>();
            }
            int val = 0;
            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if((u == g || u == h) && (v == g || v == h))
                {
                    val = d;
                }

                graph[u].add(new int[]{v,d});
                graph[v].add(new int[]{u,d});
            }
            candidate = new int[t];
            for(int k=0; k<t; k++)
            {
                candidate[k] = Integer.parseInt(br.readLine());
            }

            int[] startS = dijkstra(s);
            int[] startG = dijkstra(g);
            int[] startH = dijkstra(h);

            for(int destination:candidate)
            {
                int minDist = startS[destination];
                int minDist1 = startS[h] + val + startG[destination];
                int minDist2 = startS[g] + val + startH[destination];
                if(minDist == minDist1 || minDist == minDist2)
                {
                    answerNode.add(destination);
                }
            }
            answerNode.sort(Comparator.comparingInt(o->o));
            for(int node:answerNode)
            {
                sb.append(node).append(" ");
            }
            sb.append('\n');
            answerNode.clear();
        }
        System.out.print(sb);
    }
    static int[] dijkstra(int start)
    {
        dist = new int[n+1];
        Arrays.fill(dist ,INF);
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
}
