//배열의 값에 들어가는 높이의 값이 1이상 100이하의 정수라고 했을 뿐이다. 높이가 0인 경우는 포함하지 말라는 의미가 아니다.
//비가 오지 않는 경우 ~ 비가 최대 높이까지 오는 경우의 안전한 영역을 구하는 문제
// 2<= N <= 100 높이 = 1이상 100이하
import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        int max = 0; //건물의 최대 높이
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        int maxSafe = 0;
        for(int i = 0; i <= max; i++)
        {
            maxSafe = Math.max(maxSafe, flood(i));
        }
        System.out.println(maxSafe);
    }
    static int flood(int height)
    {
        int safe = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(map[i][j] > height && !visited[i][j])
                {
                    visited[i][j] = true;
                    q.offer(new int[]{i, j});
                    safe++;
                    while (!q.isEmpty())
                    {
                        int[] cur = q.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        for(int dir=0; dir<4; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];
                            if(OOB(nX,nY) || visited[nX][nY] || map[nX][nY] <= height) continue;
                            q.offer(new int[]{nX, nY});
                            visited[nX][nY] = true;
                        }
                    }
                }
            }
        }
        return safe;
    }
    static boolean OOB(int x, int y)
    {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}

