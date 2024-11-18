package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_23793
{
    static int n,m,x,y,z;
    static long[] cost;
    static ArrayList<int[]>[] adjList;
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cost = new long[n+1];
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[u].add(new int[]{v,w});
        }

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        long ans = 0;
        long ans_sub1 = dijkstra(x,y,0);
        long ans_sub2 = dijkstra(y,z,0);

        ans = ans_sub1 == -1 || ans_sub2 == -1 ? -1 : ans_sub1 + ans_sub2;

        long ans2 = dijkstra(x,z,1);

        StringBuilder sb = new StringBuilder();
        sb.append(ans + " " + ans2);
        System.out.print(sb);
    }

    static long dijkstra(int start,int end,int pass)
    {
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{start,0});
        Arrays.fill(cost, INF);
        cost[start] = 0;
        while (!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curWeight = cur[1];

            if(curWeight > cost[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextWeight = next[1];
                if(pass == 1 && nextNode == y) continue;
                if(cost[nextNode] > cost[curNode] + nextWeight)
                {
                    cost[nextNode] = cost[curNode] + nextWeight;
                    pq.add(new long[]{nextNode, cost[nextNode]});
                }
            }
        }
        return cost[end] == INF ? -1 : cost[end];
    }
}
