package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_2842
{
    static int n;
    static int postX, postY;
    static char[][] map;
    static int[][] cost;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0,-1,-1,1,1};
    static int[] dy = {0,1,0,-1,1,-1,1,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        cost = new int[n][n];

        int countHouse = 0;
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'K')
                    countHouse++;
                else if(map[i][j] == 'P')
                {
                    postX = i;
                    postY = j;
                }
            }
        }

        TreeSet<Integer> set = new TreeSet<>(); // TreeSet은 자동으로 오름차순 정렬
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                cost[i][j] = Integer.parseInt(st.nextToken());
                set.add(cost[i][j]);
            }
        }

        int[] high = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();
        for(int i=0; i<set.size(); i++)
        {
            high[i] = iterator.next();
        }

        int result = 1000001;
        int start = 0;
        int end = 0;

        while (start < high.length && end < high.length)
        {
            if(bfs(high[end], high[start], countHouse))
            {
                result = Math.min(result, high[end] - high[start]);
                start++;
            }
            else
                end++;
        }
        System.out.print(result);
    }
    static boolean bfs(int maxVal, int minVal, int house)
    {
        if(cost[postX][postY] < minVal || cost[postX][postY] > maxVal) return false;

        int checkHouse = 0;

        visit = new boolean[n][n];
        visit[postX][postY] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{postX, postY});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<8; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || cost[nX][nY] < minVal || cost[nX][nY] > maxVal) continue;
                visit[nX][nY] = true;
                q.add(new int[]{nX,nY});
                if(map[nX][nY] == 'K') checkHouse++;
                
            }
        }
        return checkHouse == house;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
