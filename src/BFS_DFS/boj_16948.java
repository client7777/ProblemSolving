package BFS_DFS;

import java.util.*;
import java.io.*;

public class boj_16948
{
    static int n;
    static int r1,c1,r2,c2;
    static int[][] dist;
    static int[] dx = {-2,-2,0,0,2,2};
    static int[] dy = {-1,1,-2,2,-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        dist = new int[n][n];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(dist[i],-1);
        }
        dist[r1][c1] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r1,c1});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            if(curX == r2 && curY == c2) break;
            for(int dir=0; dir<6; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || dist[nX][nY] != -1) continue;
                q.add(new int[]{nX,nY});
                dist[nX][nY] = dist[curX][curY] + 1;
            }
        }
        System.out.print(dist[r2][c2]);
    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y<0 || x >= n || y >= n;
    }
}
