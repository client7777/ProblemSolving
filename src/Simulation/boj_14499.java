package Simulation;

import java.io.*;
import java.util.*;

public class boj_14499
{
    static int n, m, x, y, k;
    static int[][] map;
    static int[] dice = new int[6]; // 주사위의 각면에 적힌 숫자를 저장하기 위한 배열
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
        {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            simulation(dir);
        }
        System.out.print(sb);
    }
    static void simulation(int dir)
    {
        int nX = x + dx[dir];
        int nY = y + dy[dir];
        if(OOB(nX,nY)) return;

        int tmp = dice[5];
        switch (dir)
        {
            case 0:
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = tmp;
                break;
            case 1:
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = tmp;
                break;

        }
        sb.append(dice[0]).append('\n');
        x = nX;
        y = nY;
        if(map[x][y] == 0)
        {
            map[x][y] = dice[5];
        }
        else
        {
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}