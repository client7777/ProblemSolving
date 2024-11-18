package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_21736
{
    static int n,m;
    static char[][] map;
    static int startX, startY;
    static int[] dx= {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'I')
                {
                    startX = i;
                    startY = j;
                }
            }
        }
        bfs();
    }
    static void bfs()
    {
        boolean[][] visit = new boolean[n][m];
        visit[startX][startY] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        int cnt = 0;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 'X') continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
                if(map[nX][nY] == 'P') cnt++; //사람을 만났을 때만 개수 증가
            }
        }
        System.out.print(cnt == 0?"TT":cnt);
    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y < 0 || x >=n || y>=m;
    }
}
