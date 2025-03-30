package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_6186
{
    static int r,c;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for(int i=0; i<r; i++)
        {
            String str = br.readLine();
            for(int j=0; j<c; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }

        visit = new boolean[r][c];

        int cnt = 0;

        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                if(map[i][j] == '#' && !visit[i][j])
                {
                    cnt++;
                    visit[i][j] = true;
                    bfs(i,j);
                }
            }
        }

        System.out.print(cnt);
    }

    static void bfs(int startX,int startY)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] != '#') continue;

                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}

