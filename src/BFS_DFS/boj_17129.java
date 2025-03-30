package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17129
{
    static int n,m;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map;
    static int startX, startY;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j) - '0';

                if(map[i][j] == 2)
                {
                    startX = i;
                    startY = j;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int minDist = bfs();
        if(minDist == -1)
        {
            sb.append("NIE");
        }
        else
        {
            sb.append("TAK").append('\n').append(minDist);
        }

        System.out.print(sb);
    }

    static int bfs()
    {
        boolean[][] visit = new boolean[n][m];
        visit[startX][startY] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == 1 || visit[nX][nY]) continue;
                if(map[nX][nY] >= 3 && map[nX][nY] <= 5) return curDist + 1;
                visit[nX][nY] = true;
                q.add(new int[]{nX,nY,curDist + 1});
            }
        }

        return -1;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
