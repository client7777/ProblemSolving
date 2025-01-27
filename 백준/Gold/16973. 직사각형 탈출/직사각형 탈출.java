import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int[][] map;
    static int h,w,sx,sy,fx,fy;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        fx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());

        System.out.print(bfs());
    }
    static int bfs()
    {
        boolean[][] visit = new boolean[n+1][m+1];
        visit[sx][sy] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx,sy,0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];

            if(curX == fx && curY == fy)
            {
                return curCnt;
            }

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1) continue;

                if(isPossible(nX,nY))
                {
                    if(map[nX][nY] == 0)
                    {
                        q.add(new int[]{nX,nY,curCnt + 1});
                        visit[nX][nY] = true;
                    }
                }
            }
        }
        return -1;
    }
    static boolean isPossible(int x,int y)
    {
        if(x + h -1 > n || y + w -1 > m) return false;

        for(int i=x; i<x+h; i++)
        {
            for(int j=y; j<y+w; j++)
            {
                if(map[i][j] == 1) return false;
            }
        }

        return true;
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
