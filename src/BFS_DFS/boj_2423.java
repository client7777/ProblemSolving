package BFS_DFS;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2423
{
    static int n,m;
    static char[][] map;
    static int[][] dist;
    static int[] dx = {-1, 1, 1, -1};
    static int[] dy = {1, 1, -1, -1}; // 상우, 하우, 하좌, 상좌
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        dist = new int[n+1][m+1];

        for(int i=0; i<n+1; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        bfs();
    }
    static void bfs()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{0,0,0});
        dist[0][0] = 0;
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];
            if(curX == n && curY == m) break;
            if(curCost > dist[curX][curY]) continue;
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY)) continue;
                int nextCost = (rotate(curX, curY, dir) ? curCost + 1 : curCost);
                if(dist[nX][nY] > nextCost)
                {
                    pq.add(new int[]{nX,nY,nextCost});
                    dist[nX][nY] = nextCost;
                }
            }
        }
        System.out.print(dist[n][m] == Integer.MAX_VALUE ? "NO SOLUTION" : dist[n][m]);
    }
    static boolean rotate(int x, int y, int dir)
    {
        if (dir == 0) return map[x - 1][y] == '\\';
        else if (dir == 1) return map[x][y] == '/';
        else if (dir == 2) return map[x][y - 1] == '\\';
        else return map[x - 1][y - 1] == '/';
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > n || y > m;
    }
}
