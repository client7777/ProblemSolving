package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1303
{
    static int n,m;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int white, blue = 0;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        visit = new boolean[m][n];
        for(int i=0; i<m; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 'W' && !visit[i][j])
                {
                    white += bfs(map[i][j],i,j);
                }
                else if(map[i][j] == 'B' && !visit[i][j])
                {
                    blue += bfs(map[i][j], i, j);
                }
            }
        }
        System.out.print(white + " " + blue);

    }
    static int bfs(char ch, int i, int j)
    {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i,j});
        visit[i][j] = true;
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || map[nX][nY] != ch || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
                cnt++;
            }
        }
        return cnt * cnt;
    }

    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= m || y >= n;
    }
}
