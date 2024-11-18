package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_18405
{
    static int n,k,s,x,y;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map;
    static ArrayList<int[]> virus = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0)
                {
                    virus.add(new int[]{i,j,map[i][j],0});
                }
            }
        }
        Collections.sort(virus, Comparator.comparingInt(o -> o[2]));
        for(int[] start:virus)
        {
            q.add(start);
        }
        st = new StringTokenizer(br.readLine());
        //s초에 x,y좌표에 존재하는 바이러스의 종류
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        bfs();
        System.out.print(map[x-1][y-1]);

    }
    static void bfs()
    {
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int v = cur[2];
            int curSec = cur[3];
            if(curSec == s) return;
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!OOB(nX,nY))
                {
                    if(map[nX][nY] == 0)
                    {
                        map[nX][nY] = v;
                        q.add(new int[]{nX,nY, v, curSec + 1});
                    }
                }
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
