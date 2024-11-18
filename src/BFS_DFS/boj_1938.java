package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_1938
{
    static int n;
    static int endX,endY,startDir,endDir;
    static char[][] map;
    static boolean[][][] visit;
    static ArrayList<int[]> start = new ArrayList<>();
    static ArrayList<int[]> end = new ArrayList<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'B') start.add(new int[]{i,j});
                if(map[i][j] == 'E') end.add(new int[]{i,j});
            }
        }
        endX = end.get(1)[0];
        endY = end.get(1)[1];
        setDir();
        bfs();
    }
    static void bfs()
    {
        int[] startPos = start.get(1);
        int startX = startPos[0];
        int startY = startPos[1];
        visit = new boolean[n][n][2];
        visit[startX][startY][startDir] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, startDir, 0});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDir = cur[2];
            int curCnt = cur[3];
            if(curX == endX && curY == endY && curDir == endDir)
            {
                System.out.print(curCnt);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(move(nX,nY,curDir))
                {
                    if(!visit[nX][nY][curDir])
                    {
                        visit[nX][nY][curDir] = true;
                        q.add(new int[]{nX,nY,curDir,curCnt + 1});
                    }
                }
            }
            if(canRotate(curX, curY))
            {
                int newDir = curDir == 0 ? 1 : 0;
                if(!visit[curX][curY][newDir])
                {
                    q.add(new int[]{curX,curY,newDir, curCnt+1});
                    visit[curX][curY][newDir] = true;
                }
            }
        }
        System.out.print(0);
    }
    static boolean canRotate(int x,int y)
    {
        for(int i=-1; i<=1; i++)
        {
            for(int j=-1; j<=1; j++)
            {
                if(OOB(x+i, y+j) || map[x+i][y+j] == '1') return false;
            }
        }
        return true;
    }
    static boolean move(int x,int y,int dir)
    {
        if(OOB(x,y) || map[x][y] == '1') return false;
        //현재 방향이 가로인 경우
        if(dir == 0)
        {
            return !(OOB(x,y-1) || OOB(x,y+1) || map[x][y-1] == '1' || map[x][y+1] == '1');
        }
        else
        {
            return !(OOB(x-1,y) || OOB(x+1,y) || map[x-1][y] == '1' || map[x+1][y] == '1');
        }
    }
    static void setDir()
    {
        int[] first = start.get(0);
        int[] second = start.get(1);
        if(first[0] == second[0])
        {
            startDir = 0;
        }
        else startDir = 1;

        first = end.get(0);
        second = end.get(1);
        if(first[0] == second[0])
        {
            endDir = 0;
        }
        else endDir = 1;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
