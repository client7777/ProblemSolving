package BFS_DFS;
//탈출
import java.io.*;
import java.util.*;

public class boj_3055
{
    static int r,c;
    static char[][] map;
    static int[][] water;
    static int[][] go;
    static Queue<int[]> waterQ = new LinkedList<>(); // 물에 대한 큐
    static Queue<int[]> goQ = new LinkedList<>(); // 고슴도치에 대한 큐
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int caveX, caveY;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        water = new int[r][c];
        go = new int[r][c];
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                go[i][j] = -1;
                water[i][j] = -1;
            }
        }

        for(int i=0; i<r; i++)
        {
            String str = br.readLine();
            for(int j=0; j<c; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '*')
                {
                    waterQ.add(new int[]{i,j});
                    water[i][j] = 0;
                }
                if(map[i][j] == 'S')
                {
                    goQ.add(new int[]{i,j});
                    go[i][j] = 0;
                }
                if(map[i][j] == 'D')
                {
                    caveX = i;
                    caveY = j;
                }
            }
        }
        waterBfs();
        goBfs();
        System.out.print(sb);
    }
    //물에 대한 bfs, 고슴도치에 대한 bfs를 따로 돌림
    static void waterBfs()
    {
        while (!waterQ.isEmpty())
        {
            int[] cur = waterQ.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX, nY) || map[nX][nY] == 'D' || map[nX][nY] == 'X' || water[nX][nY] != -1) continue;
                waterQ.add(new int[]{nX,nY});
                water[nX][nY] = water[curX][curY] + 1;
            }
        }
    }
    static void goBfs()
    {
        while (!goQ.isEmpty())
        {
            int[] cur = goQ.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || go[nX][nY] != -1 || map[nX][nY] == 'X' || map[nX][nY] == '*') continue;
                //고슴도치는 이미 물이 차있는 칸이거나, 물이 찰 예정인 칸에 갈 수 없다.
                if(water[nX][nY] <= go[curX][curY] + 1 && water[nX][nY] != -1) continue;

                if(map[nX][nY] == 'D')
                {
                    sb.append(go[curX][curY] + 1);
                    return;
                }

                goQ.add(new int[]{nX,nY});
                go[nX][nY] = go[curX][curY] + 1;
            }
        }
        sb.append("KAKTUS");
        return;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}
