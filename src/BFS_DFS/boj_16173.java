package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_16173
{
    static int n;
    static int[][] map;
    static int startX = 0, startY = 0;
    //이동 가능한 방향은 오른쪽, 아래뿐
    static int[] dx = {0,1};
    static int[] dy = {1,0};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, map[startX][startY]});
        boolean[][] visit = new boolean[n][n];
        visit[startX][startY] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curStep = cur[2];
            if(curX == n-1 && curY == n-1)
            {
                System.out.print("HaruHaru");
                return;
            }
            for(int dir=0; dir<2; dir++)
            {
                int nX = curX + dx[dir] * curStep;
                int nY = curY + dy[dir] * curStep;
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY,map[nX][nY]});
                visit[nX][nY] = true;
            }
        }
        System.out.print("Hing");
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
