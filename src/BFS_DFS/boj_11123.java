package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11123
{
    static char[][] map;
    static boolean[][] visit;
    static int h,w;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            visit = new boolean[h][w];

            for(int i=0; i<h; i++)
            {
                String str = br.readLine();
                for(int j=0; j<w; j++)
                {
                    map[i][j] = str.charAt(j);
                }
            }
            int cnt = 0;
            for(int i=0; i<h; i++)
            {
                for(int j=0; j<w; j++)
                {
                    if(map[i][j] == '#' && !visit[i][j])
                    {
                        cnt++;
                        bfs(i,j);
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
    static void bfs(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        visit[x][y] = true;
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
                if(OOB(nX,nY) || map[nX][nY] == '.' || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= h || y >= w;
    }
}
