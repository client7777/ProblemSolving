package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_22116
{
    static int n;
    static int INF = Integer.MAX_VALUE / 2;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        map = new int[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijkstra();
    }

    static void dijkstra()
    {
        //현재까지 이동하면서 만난 최대 높이 차이
        int[][] dist = new int[n+1][n+1];
        for(int[] row:dist)
        {
            Arrays.fill(row, INF);
        }
        dist[1][1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,1,0));

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDiff = cur.diff;

            if(curDiff > dist[curX][curY]) continue;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;

                int nextDiff = Math.abs(map[curX][curY] - map[nX][nY]);

                if(dist[nX][nY] > Math.max(curDiff, nextDiff))
                {
                    dist[nX][nY] = Math.max(curDiff, nextDiff);
                    pq.add(new Edge(nX,nY,dist[nX][nY]));
                }
            }
        }

        System.out.print(dist[n][n]);
    }

    static class Edge implements Comparable<Edge>
    {
        int x;
        int y;
        int diff;

        public Edge(int x, int y, int cost)
        {
            this.x = x;
            this.y = y;
            this.diff = cost;
        }

        @Override
        public int compareTo(Edge o)
        {
            return Integer.compare(this.diff, o.diff);
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > n;
    }
}