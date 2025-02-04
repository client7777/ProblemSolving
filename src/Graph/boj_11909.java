package Graph;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_11909
{
    static int n;
    static int[][] map, dist;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dist = new int[n+1][n+1];

        map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijkstra();
        System.out.print(dist[n][n]);
    }

    static void dijkstra()
    {
        for(int i=1; i<=n; i++)
        {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[1][1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,1,0));

        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curCost = cur.cost;

            if(curCost > dist[curX][curY]) continue;

            for(int dir=0; dir<2; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nextCost = curCost;

                if(OOB(nX,nY)) continue;

                if(map[nX][nY] >= map[curX][curY])
                {
                    nextCost += map[nX][nY] - map[curX][curY] + 1;
                }

                if(dist[nX][nY] > nextCost)
                {
                    dist[nX][nY] = nextCost;
                    pq.add(new Edge(nX,nY,dist[nX][nY]));
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > n;
    }

    static class Edge implements Comparable<Edge>
    {
        int x;
        int y;
        int cost;

        public Edge(int x, int y, int cost)
        {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o)
        {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
