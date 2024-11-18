package BFS_DFS;
import java.io.*;
import java.util.*;
//토마토
public class boj_7576
{
    static int m,n;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        // 0은 익지 않은 토마토, 1은 익은 토마토(탐색 시작점), -1은 토마토가 들어있지 않은 칸

        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                {
                    q.add(new int[]{i, j});
                }

            }
        }
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir = 0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX, nY) || map[nX][nY] != 0) continue;
                q.add(new int[]{nX, nY});
                map[nX][nY] = map[curX][curY] + 1;
            }
        }
        int min_day = 0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(map[i][j] == 0)
                {
                    System.out.print(-1);
                    return;
                }
                min_day = Math.max(min_day, map[i][j]);
            }
        }
        if(min_day == 1)
        {
            System.out.print(0);
            return;
        }
        System.out.print(min_day - 1);
    }
    static boolean OOB(int x,int y)
    {
        return  x < 0 || y < 0 || x >= n || y >= m;
    }
}
