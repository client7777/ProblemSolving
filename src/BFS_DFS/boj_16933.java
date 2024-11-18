package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_16933
{
    static int n, m, k, ans;
    static int[][] map;
    static boolean[][][][] visit;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m][k+1][2]; // x,y,부순 벽의 수, 낮,밤 체크

        for (int i = 0; i < n; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < m; j++)
            {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        ans = -1; // 도달할 수 없는 경우 -1 출력
        bfs();
        System.out.print(ans);
    }
    static void bfs()
    {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,1,0,0}); // x,y,dist,wall,day
        visit[0][0][0][0] = true;
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int dist = cur[2];
            int wall = cur[3];
            int day = cur[4];
            // 목표 지점에 도착한 경우
            if(curX == n-1 && curY == m-1)
            {
                ans = dist;
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(nX < 0 || nY < 0 || nX >= n || nY >= m) continue;
                // 빈 공간으로 이동할 경우
                if(map[nX][nY] == 0)
                {
                    // 낮일 때
                    if(day == 0 && !visit[nX][nY][wall][day+1])
                    {
                        q.add(new int[]{nX,nY,dist+1,wall,day+1});
                        visit[nX][nY][wall][day+1] = true;
                    }
                    else if(day == 1 && !visit[nX][nY][wall][day-1])
                    {
                        q.add(new int[]{nX,nY,dist+1,wall,day-1});
                        visit[nX][nY][wall][day-1] = true;
                    }
                }
                // 벽일 경우
                else
                {
                    // 부술 수 있는 벽이 남아있고 낮인 경우
                    if(wall < k && day == 0 && !visit[nX][nY][wall+1][day+1])
                    {
                        visit[nX][nY][wall+1][day+1] = true; // 벽을 부수고 이동
                        q.add(new int[]{nX,nY,dist+1,wall+1,day+1});
                    }
                    // 부술 수 있는 벽이 남아있고 밤인 경우(밤에는 현재 좌표에서 대기)
                    else if(wall < k && day == 1 && !visit[curX][curY][wall][day-1])
                    {
                        //현재 위치에서 대기, 방문처리
                        visit[curX][curY][wall][day-1] = true;
                        q.add(new int[]{curX,curY,dist+1,wall,day-1});
                    }
                }
            }
        }
    }
}
