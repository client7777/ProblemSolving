package BFS_DFS;
//3차원 배열을 사용해서 방문여부를 체크할 경우 최악의 경우 50*50번을 매번 이동할 때마다 체크해야 하므로 적절하지 않음
//다익스트라 알고리즘을 통해 최소비용 가중치가 낮은 큐를 우선적으로 방문
import java.io.*;
import java.util.*;

public class boj_2665
{
    static int n;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs();
    }
    static void bfs()
    {
        int[][] dist = new int[n][n];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{0,0,0}); // 좌표와 방의 색을 바꾼 횟수
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curRoom = cur[2];
            if(curX == n-1 && curY == n-1)
            {
                System.out.print(curRoom);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY)) continue;
                int newRoom = curRoom + (map[nX][nY] == 0 ? 1 : 0);
                if(newRoom < dist[nX][nY])
                {
                    dist[nX][nY] = newRoom;
                    pq.add(new int[]{nX,nY,newRoom});
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y < 0 || x >= n || y >= n;
    }
}
