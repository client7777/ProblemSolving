package Graph;

import java.io.*;
import java.util.*;

public class boj_1486
{
    static int n,m,t,d;
    static int INF = 1000000;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        t = Integer.parseInt(st.nextToken()); // 통행 높이의 상한선
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                char val = str.charAt(j);

                if (val >= 'A' && val <= 'Z')
                {
                    map[i][j] = val - 'A';
                } else if (val >= 'a' && val <= 'z')
                {
                    map[i][j] = val - 'a' + 26;
                }
            }
        }

        int[][] dist = dijkstra(0,0);

        int max = Integer.MIN_VALUE;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(dist[i][j] + reverse(i,j) <= d)
                {
                    max = Math.max(max, map[i][j]);
                }
            }
        }

        System.out.print(max);
    }
    static int[][] dijkstra(int x, int y)
    {
        int[][] dist = new int[n][m];
        for(int[] row:dist)
        {
            Arrays.fill(row, INF);
        }
        dist[x][y] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{x,y,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curTime = cur[2];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;

                int diff = Math.abs(map[curX][curY] - map[nX][nY]);

                if(diff > t) continue;

               if(map[curX][curY] >= map[nX][nY])
               {
                   int nextTime = curTime + 1;
                   if(dist[nX][nY] > nextTime)
                   {
                       dist[nX][nY] = nextTime;
                       pq.add(new int[]{nX,nY,nextTime});
                   }
               }
               else
               {
                   int nextTime = curTime + diff * diff;
                   if(dist[nX][nY] > nextTime)
                   {
                       dist[nX][nY] = nextTime;
                       pq.add(new int[]{nX,nY,nextTime});
                   }
               }
            }
        }
        return dist;
    }

    static int reverse(int x,int y)
    {
        int[][] dist = new int[n][m];
        for(int[] row:dist)
        {
            Arrays.fill(row, INF);
        }
        dist[x][y] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{x,y,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curTime = cur[2];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;

                int diff = Math.abs(map[curX][curY] - map[nX][nY]);

                if(diff > t) continue;

                if(map[curX][curY] >= map[nX][nY])
                {
                    int nextTime = curTime + 1;
                    if(dist[nX][nY] > nextTime)
                    {
                        dist[nX][nY] = nextTime;
                        pq.add(new int[]{nX,nY,nextTime});
                    }
                }
                else
                {
                    int nextTime = curTime + diff * diff;
                    if(dist[nX][nY] > nextTime)
                    {
                        dist[nX][nY] = nextTime;
                        pq.add(new int[]{nX,nY,nextTime});
                    }
                }
            }
        }
        return dist[0][0];
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
