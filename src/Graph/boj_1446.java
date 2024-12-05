package Graph;

import java.io.*;
import java.util.*;

public class boj_1446
{
    static int n,d;
    static ArrayList<int[]>[] graph;
    static int[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new ArrayList[d+1];
        for(int i=0; i<=d; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n; i++)
        {
            st =  new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(v > d) continue;
            graph[u].add(new int[]{v,w});
        }
        dijkstra();
    }
    static void dijkstra()
    {
        dist = new int[d+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{0,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curPos = cur[0];
            int curCost = cur[1];

            if(curCost > dist[curPos]) continue;

            // 거리 1만큼 이동
            if(curPos + 1 <= d && curCost + 1 < dist[curPos+1])
            {
                dist[curPos+1] = curCost + 1;
                pq.add(new int[]{curPos + 1, curCost + 1});
            }

            for(int[] fastWay:graph[curPos])
            {
                int nextPos = fastWay[0]; // 지름길 도착 위치
                int nextCost = fastWay[1]; // 지름길의 길이
                int totalCost = curCost + nextCost; // 현재 위치까지의 최소 비용 + 현재 위치에서 지름길을 이용했을 때 비용
                if(totalCost < dist[nextPos])
                {
                    dist[nextPos] = totalCost;
                    pq.add(new int[]{nextPos, totalCost});
                }
            }
        }
        System.out.print(dist[d]);
    }
}
/*
public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] dp = new int[10001];

        for(int i=0; i<=d; i++)
        {
            dp[i] = i;
        }

        int[][] arr = new int[n][3];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++)
            {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=d; i++)
        {
            dp[i] = Math.min(dp[i], dp[i-1] + 1);

            for(int j=0; j<n; j++)
            {
                if(arr[j][1] == i)
                {
                    dp[i] = Math.min(dp[i], dp[arr[j][0]] + arr[j][2]);
                }
            }
        }
        System.out.print(dp[d]);
    }
*/
