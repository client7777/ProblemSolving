package BFS_DFS;
// 어떤 빈칸의 상하좌우 중 하나가 벽이면 벽에 인접한 칸임
// 벽을 타고 이동하면 가중치 0으로 이동 가능 -> 벽에 인접한 칸에서 벽에 인접한 칸으로 이동할 경우
// 그 외의 경우 이동할 때 가중치 1
import java.io.*;
import java.util.*;

public class boj_23563
{
    static int n,m;
    static int[][] dist;
    static char[][] map;
    static int startX, startY, endX, endY;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        dist = new int[n][m];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S')
                {
                    startX = i;
                    startY = j;
                }
                if(map[i][j] == 'E')
                {
                    endX = i;
                    endY = j;
                }
            }
        }
        bfs();
    }
    static void bfs()
    {
        dist[startX][startY] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{startX, startY, 0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];

            if(curCost > dist[curX][curY]) continue;

            if(curX == endX && curY == endY)
            {
                System.out.print(dist[endX][endY]);
            }
            boolean curFlag = check_wall(curX, curY);

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == '#') continue;

                boolean nextFlag = check_wall(nX,nY);

                if(curFlag && nextFlag)
                {
                    if(dist[nX][nY] > curCost)
                    {
                        pq.add(new int[]{nX,nY,curCost});
                        dist[nX][nY] = curCost;
                    }
                }
                else
                {
                    if(dist[nX][nY] > curCost + 1)
                    {
                        pq.add(new int[]{nX,nY,curCost + 1});
                        dist[nX][nY] = curCost + 1;
                    }
                }

            }
        }
    }
    static boolean check_wall(int x,int y)
    {
        int cnt = 0;
        for(int dir=0; dir<4; dir++)
        {
            int nX = x + dx[dir];
            int nY = y + dy[dir];
            if(OOB(nX,nY)) continue;
            if(map[nX][nY] == '#') cnt++;
        }
        return cnt > 0;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 ||  y < 0 || x >= n || y >= m;
    }
}
