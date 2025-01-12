package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14923
{
    static int n,m;
    static int startX, startY, endX, endY;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }
    static void bfs()
    {
        boolean[][][] visit = new boolean[n+1][m+1][2];
        visit[startX][startY][0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curWall = cur[2];
            int curDist = cur[3];

            if(curX == endX && curY == endY)
            {
                System.out.print(curDist);
                return;
            }

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY][curWall]) continue;

                if(map[nX][nY] == 1)
                {
                    if(curWall == 0 && !visit[nX][nY][1])
                    {
                        q.add(new int[]{nX,nY,1,curDist + 1});
                        visit[nX][nY][1] = true;
                    }
                }
                else
                {
                    q.add(new int[]{nX,nY,curWall, curDist + 1});
                    visit[nX][nY][curWall] = true;
                }
            }
        }
        System.out.print(-1);
    }
    static boolean OOB(int x, int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
