package BFS_DFS;

import java.util.*;
import java.io.*;

public class boj_17836
{
    static int n,m,t;
    static int gramX, gramY;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                {
                    gramX = i;
                    gramY = j;
                }
            }
        }
        bfs();
    }
    static void bfs()
    {
        boolean[][][] visit = new boolean[n+1][m+1][2]; // 0 = 그람을 먹지 않은 상태, 1 = 그람을 먹은 상태
        Queue<int[]> q =  new LinkedList<>();
        q.add(new int[]{1,1,0,0});
        visit[1][1][0] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curTime = cur[2];
            int hasGram = cur[3];
            //t시간을 초과하면 구출 실패
            if(curTime > t)
            {
                System.out.print("Fail");
                return;
            }
            if(curX == n && curY == m)
            {
                System.out.print(curTime);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!OOB(nX,nY))
                {
                    //그람을 먹은 상태와 먹지 않은 상태에 대한 탐색을 구분
                    if(hasGram == 0 && !visit[nX][nY][0] && map[nX][nY] != 1)
                    {
                        q.add(new int[]{nX,nY,curTime + 1,0});
                        visit[nX][nY][0] = true;
                    }
                    if(hasGram == 1 && !visit[nX][nY][1])
                    {
                        q.add(new int[]{nX,nY,curTime + 1,1});
                        visit[nX][nY][1] = true;
                    }
                    //그람을 찾았을 때 큐에 넣고 이후 탐색에서 벽을 통과할 수 있도록 해야 함
                    if(map[nX][nY] == 2 && !visit[nX][nY][1])
                    {
                        q.add(new int[]{nX,nY,curTime + 1,1});
                        visit[nX][nY][1] = true;
                    }
                }
            }
        }
        System.out.print("Fail");
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
