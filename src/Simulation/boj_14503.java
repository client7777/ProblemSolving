package Simulation;

import java.io.*;
import java.util.*;

public class boj_14503
{
    static int n,m;
    static int startX,startY,startDir;
    static int[][] map;
    static int[] dx = {-1,0,1,0}; // 북동남서
    static int[] dy = {0,1,0,-1};
    static int cnt = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startDir = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(startX, startY, startDir);
        System.out.print(cnt);
    }
    static void clean(int x,int y,int dir)
    {
        if(map[x][y] == 0)
        {
            cnt++;
            map[x][y] = 2;
        }
        for(int i=0; i<4; i++)
        {
            // 반시계 방향으로 90도 회전
            // 현재 바라보고 있는 방향에 따라 탐색 순서가 달라짐
            dir = (dir+3)%4;
            int nX = x + dx[dir];
            int nY = y + dy[dir];

            if(!OOB(nX,nY) && map[nX][nY] == 0)
            {
                //청소할 수 있는 칸을 발견하면 재귀호출을 하여 그 칸에서 청소를 시작
                //더이상 다른 방향에 대한 탐색을 할 필요가 없으므로 return을 통해 탐색 중지
                clean(nX,nY,dir);
                return;
            }
        }
        int backDir = (dir+2) % 4;
        int backX = x + dx[backDir];
        int backY = y + dy[backDir];

        if(!OOB(backX, backY) && map[backX][backY] != 1)
        {
            // 후진 후에도 추가적인 청소작업을 계속할 수 있도록 함
            // 후진 후에 로봇은 새로운 위치에서 다시 주변을 탐색 return을 사용하면 후진 후 탐색이 종료됨
            clean(backX,backY, dir);
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
