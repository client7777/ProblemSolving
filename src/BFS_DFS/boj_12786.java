package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_12786
{
    static int n,t;
    static boolean[][] hole;
    static int[][] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        t = Integer.parseInt(br.readLine());

        hole = new boolean[101][21];
        dist = new int[101][21];

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0; j<cnt; j++)
            {
                hole[i+1][Integer.parseInt(st.nextToken())] = true;
            }
        }

        for(int i=0; i<n+1; i++)
        {
            for(int j=1; j<21; j++)
            {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dijkstra();
    }
    static void dijkstra()
    {
       dist[0][1] = 0;
       PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
       pq.add(new int[]{0,1,0});

       while (!pq.isEmpty())
       {
           // 나무의 번호는 n을 초과하면 안됨. 구멍의 높이는 자연수
           int[] cur = pq.poll();
           int curNum = cur[0];
           int curHeight = cur[1];
           int  curCost = cur[2];

           if(curNum == n) break;

           if(curCost > dist[curNum][curHeight]) continue;

           int nextNum = curNum + 1;

           int[] height = {curHeight, curHeight + 1, curHeight -1, Math.min(20, curHeight + curHeight)};

           //t 버튼을 제외한 나머지 버튼
           for(int nextHeight:height)
           {
               if(OOB(nextNum, nextHeight)) continue;
               if(hole[nextNum][nextHeight] && dist[nextNum][nextHeight] > curCost)
               {
                   pq.add(new int[]{nextNum, nextHeight, curCost});
                   dist[nextNum][nextHeight] = curCost;
               }
           }
           //t 버튼
           for(int i=1; i<=20; i++)
           {
               if(!OOB(nextNum, i) && hole[nextNum][i] && dist[nextNum][i] > curCost + 1)
               {
                   pq.add(new int[]{nextNum, i, curCost + 1});
                   dist[nextNum][i] = curCost + 1;
               }
           }
       }
       int ans = Integer.MAX_VALUE;
       for(int i=1; i<=20; i++)
       {
           ans = Math.min(ans, dist[n][i]);
       }
       System.out.print(ans <= t ? ans : -1);
    }
    static boolean OOB(int num,int height)
    {
        return num > n || height < 1 || height > 20;
    }
}
