package BFS_DFS;
// 미로 탐색
// 시간복잡도는 = O(nm)
import java.io.*;
import java.util.*;

public class boj_2178
{
    static int[][] map;
    static boolean[][] visit;
    static int n,m;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for(int i=0;i<n;i++)
        {
            String str = br.readLine();
            for(int j=0;j<m;j++)
            {
                map[i][j] = str.charAt(j)-'0';
            }
        }
        //출발 -> 0,0 목적지 -> n-1,m-1
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        visit[0][0] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            if(curX == n-1 && curY == m-1)
            {
                System.out.print(curDist);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY]==0) continue;
                q.add(new int[]{nX,nY,curDist+1});
                visit[nX][nY] = true;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}

