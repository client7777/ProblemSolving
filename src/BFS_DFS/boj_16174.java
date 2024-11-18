package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16174
{
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    static int n;
    static int[][] map;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,map[0][0]});
        boolean[][] visit = new boolean[n][n];
        visit[0][0] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curStep = cur[2];
            if(curX == n-1 && curY == n-1)
            {
                System.out.print("HaruHaru");
                return;
            }
            for(int dir=0; dir<2; dir++)
            {
                int nX = curX + dx[dir] * curStep;
                int nY = curY + dy[dir] * curStep;
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY,map[nX][nY]});
                visit[nX][nY] = true;
            }
        }
        System.out.print("Hing");
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
