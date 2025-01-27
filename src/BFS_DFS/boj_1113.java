package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1113
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j) - '0';
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int ans = 0;

        for(int h=minHeight; h<=maxHeight; h++)
        {
            for(int i=1; i<n-1; i++)
            {
                for(int j=1; j<m-1; j++)
                {
                    if(map[i][j] == h)
                    {
                        ans += bfs(h,i,j);
                    }
                }
            }
        }
        System.out.print(ans);
    }

    static int bfs(int h, int x, int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        map[x][y] = h + 1;

        int cnt = 1;

        boolean possible = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] < h)
                {
                    possible = false;
                    continue;
                }

                if(map[nX][nY] != h) continue;

                map[nX][nY] = h + 1;

                q.add(new int[]{nX,nY});
                cnt++;
            }
        }

        return possible ? cnt : 0;

    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}

