package BFS_DFS;
//루프를 돌면서 빙산의 개수를 count -> 빙산의 개수가 2이상이 되는 순간 최소 년수를 출력하고 루프 탈출,
//빙산이 다 녹아도 2덩어리 이상으로 분리되지 않으면 0을 출력
//빙산의 개수를 새는 연산, 빙산을 녹이는 연산
import java.io.*;
import java.util.*;

public class boj_2573
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static  void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        while (true)
        {
            int count = countIce();
            if(count >= 2)
            {
                System.out.print(year);
                return;
            }
            else if(count == 0)
            {
                System.out.print(0);
                return;
            }
            melt();
            year++;
        }
    }
    static int countIce()
    {
        int cnt = 0;
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(map[i][j] != 0 && !visited[i][j])
                {
                    cnt++;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                    while (!q.isEmpty())
                    {
                        int[] curr = q.poll();
                        int curX = curr[0];
                        int curY = curr[1];
                        for(int dir=0; dir<4; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];
                            if(OOB(nX,nY) || visited[nX][nY] || map[nX][nY] == 0) continue;
                            q.add(new int[]{nX,nY});
                            visited[nX][nY] = true;
                        }
                    }
                }
            }
        }
        return cnt;
    }
    static void melt()
    {
        int[][] copy = new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(map[i][j] != 0)
                {
                    int cnt = 0;
                    for(int dir=0; dir<4; dir++)
                    {
                        int nI = i + dx[dir];
                        int nJ = j + dy[dir];
                        if(!OOB(nI,nJ) && map[nI][nJ] == 0)
                        {
                            cnt++;
                        }
                    }
                    copy[i][j] = Math.max(copy[i][j],map[i][j] - cnt);
                }
            }
        }
        for(int i=0;i<n;i++)
        {
            System.arraycopy(copy[i], 0, map[i], 0, m);
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
