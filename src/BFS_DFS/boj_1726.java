package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_1726
{
    static int n,m;
    static int[][] map;
    static int startX, startY, startDir;
    static int goalX, goalY, goalDir;
    static boolean[][][] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m+1][n+1];
        visit = new boolean[m+1][n+1][5]; // 방향은 1~4
        for(int i=1; i<=m; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startDir = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        goalX = Integer.parseInt(st.nextToken());
        goalY = Integer.parseInt(st.nextToken());
        goalDir = Integer.parseInt(st.nextToken());
        bfs();

    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, startDir, 0});
        visit[startX][startY][startDir] = true;
        //동 -> 서 -> 남 -> 순
        int[] turnLeft = {0,4,3,1,2};
        int[] turnRight = {0,3,4,2,1};
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDir = cur[2];
            int curCnt = cur[3];
            if(curX == goalX && curY == goalY && curDir == goalDir)
            {
                System.out.print(curCnt);
                return;
            }
            //직진은 해당 방향으로  1~3칸까지 가능
            for(int dir=1; dir<=3; dir++)
            {
                int nX = curX;
                int nY = curY;

                // 1 = 동, 2 = 서, 3 = 남, 4 = 북
                if(curDir == 1) nY += dir;
                else if(curDir == 2) nY -= dir;
                else if(curDir == 3) nX += dir;
                else nX -= dir;
                
                //범위체크가 먼저
                if(OOB(nX,nY)) continue;
                //해당 방향으로 이동을 진행하다 벽을 만나면 탐색을 끝내야 함
                if(map[nX][nY] == 1) break;
                if(!visit[nX][nY][curDir])
                {
                    q.add(new int[]{nX,nY,curDir, curCnt + 1});
                    visit[nX][nY][curDir] = true;
                }
            }
            //1~3칸 이동한 뒤에 좌, 우회전
            int newDir = turnLeft[curDir];
            if(!visit[curX][curY][newDir])
            {
                q.add(new int[]{curX,curY,newDir,curCnt + 1});
                visit[curX][curY][newDir] = true;
            }
            newDir = turnRight[curDir];
            if(!visit[curX][curY][newDir])
            {
                q.add(new int[]{curX,curY,newDir,curCnt + 1});
                visit[curX][curY][newDir] = true;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > m || y > n;
    }
}
