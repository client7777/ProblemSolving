package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_21938
{
    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                int average = (r + g + b) / 3;
                map[i][j] = average;
            }
        }

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                //경계값 이상이면 255, 아니라면 0
                if(map[i][j] >= T) map[i][j] = 255;
                else map[i][j] = 0;
            }
        }

        visit = new boolean[n][m];

        int cnt = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                //방문한 지점이 아니고 255인 좌표에서 탐색 시작
                if(!visit[i][j] && map[i][j] == 255)
                {
                    cnt++;
                    bfs(i,j);
                }
            }
        }

        System.out.print(cnt);
    }

    static void bfs(int x, int y)
    {
        visit[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] != 255 || visit[nX][nY]) continue;

                visit[nX][nY] = true;
                q.add(new int[]{nX,nY});
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return  x < 0 || y < 0 || x >= n || y >= m;
    }
}
