import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        int max = -1;
        int min = 201;

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        int start = 0;
        int end = max - min;

        while (start <= end)
        {
            int mid = (start + end) / 2;

            boolean flag = false;

            for(int i=min; i+mid <= max; i++)
            {
                if(bfs(i, i + mid))
                {
                    flag = true;
                    break;
                }
            }
            if(flag)
                end = mid - 1;
            else
                start = mid + 1;
        }
        System.out.print(start);
    }
    static boolean bfs(int minVal, int maxVal)
    {
        if(map[0][0] < minVal || map[0][0] > maxVal) return false;

        visit = new boolean[n][n];
        visit[0][0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            if(curX == n-1 && curY == n-1) return true;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] < minVal || map[nX][nY] > maxVal) continue;

                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
        }
        return false;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}