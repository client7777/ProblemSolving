package BFS_DFS;
//보물섬 시간복잡도 = O((n*m)^2) 백트래킹 + bfs 풀이는 시간초과
// L을 찾는 과정에서 n*m의 시간복잡도 발생, 각 L에 대해서 bfs를 수행하는 과정에서 n*m의 시간복잡도 발생
import java.io.*;
import java.util.*;

public class boj_2589
{
    static int n,m;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int max_Dist = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 'L')
                {
                    bfs(i,j);
                }
            }
        }
        System.out.print(max_Dist);
    }
    static void bfs(int x,int y)
    {
        boolean[][] visit = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,0});
        visit[x][y] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            max_Dist = Math.max(max_Dist, curDist);
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 'W') continue;
                q.add(new int[]{nX, nY, curDist+1});
                visit[nX][nY] = true;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
