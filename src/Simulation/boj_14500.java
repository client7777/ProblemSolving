package Simulation;

import java.io.*;
import java.util.StringTokenizer;

public class boj_14500
{
    static int n,m;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                visit[i][j] = true;
                backTrack(i,j,map[i][j],1);
                visit[i][j] = false;
            }
        }
        System.out.print(max);
    }
    static void backTrack(int x,int y,int sum, int depth)
    {
        if(depth == 4)
        {
            max = Math.max(max, sum);
            return;
        }
        for(int dir=0; dir<4; dir++)
        {
            int nX = x + dx[dir];
            int nY = y + dy[dir];

            if(OOB(nX,nY) || visit[nX][nY]) continue;

            if(depth == 2)
            {
                visit[nX][nY] = true;
                backTrack(x,y,sum + map[nX][nY], depth + 1);
                visit[nX][nY] = false;
            }
            visit[nX][nY] = true;
            backTrack(nX,nY,sum + map[nX][nY], depth + 1);
            visit[nX][nY] = false;
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
