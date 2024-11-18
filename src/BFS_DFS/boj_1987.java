package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_1987
{
    static int r,c;
    static int maxCnt = 1;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[] visitAlpha = new boolean[26];

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r+1][c+1];
        for(int i=1; i<r+1; i++)
        {
            String str = br.readLine();
            for(int j=1; j<c+1; j++)
            {
                map[i][j] = str.charAt(j-1) - 'A';
            }
        }
        backTrack(1,1,1);
        System.out.print(maxCnt);
    }
    static void backTrack(int x,int y,int cnt)
    {
        visitAlpha[map[x][y]] = true;
        maxCnt = Math.max(maxCnt, cnt);

        for(int dir=0; dir<4; dir++)
        {
            int nX = x + dx[dir];
            int nY = y + dy[dir];
            if(OOB(nX,nY)) continue;
            if(!visitAlpha[map[nX][nY]])
            {
                backTrack(nX,nY,cnt+1);
            }
        }
        visitAlpha[map[x][y]] = false;
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x >= r+1 || y >= c+1;
    }
}

/*
경로를 추적하는 코드를 추가함

public class boj_1987
{
    static int r,c;
    static int maxCnt = 1;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[] visitAlpha = new boolean[26];
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Character> path = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r+1][c+1];
        for(int i=1; i<r+1; i++)
        {
            String str = br.readLine();
            for(int j=1; j<c+1; j++)
            {
                map[i][j] = str.charAt(j-1);
            }
        }
        backTrack(1,1,1);
        System.out.println(maxCnt);
        System.out.print(sb);
    }
    static void backTrack(int x,int y,int cnt)
    {
        visitAlpha[map[x][y] - 'A'] = true;
        maxCnt = Math.max(maxCnt, cnt);
        path.add(map[x][y]);
        sb.append(path).append('\n');
        for(int dir=0; dir<4; dir++)
        {
            int nX = x + dx[dir];
            int nY = y + dy[dir];
            if(OOB(nX,nY)) continue;
            if(!visitAlpha[map[nX][nY] - 'A'])
            {
                backTrack(nX,nY,cnt+1);
            }
        }
        visitAlpha[map[x][y] - 'A'] = false;
        path.remove(path.size() - 1);
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x >= r+1 || y >= c+1;
    }
}

*/