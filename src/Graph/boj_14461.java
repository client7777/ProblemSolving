package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_14461
{
    static int n,t;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][][] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijkstra();
        System.out.print(Math.min(dist[n-1][n-1][0], Math.min(dist[n-1][n-1][1], dist[n-1][n-1][2])));
    }

    static void dijkstra()
    {
        dist = new int[n][n][3];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        dist[0][0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curMove = cur.move;
            int curTime = cur.time;

            if(curTime > dist[curX][curY][curMove]) continue;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;

                int nextMove = curMove + 1;
                int nextTime = curTime + t;

                if(nextMove == 3)
                {
                    nextTime += map[nX][nY];
                    nextMove = 0;
                }

                if(dist[nX][nY][nextMove] > nextTime)
                {
                    dist[nX][nY][nextMove] = nextTime;
                    pq.add(new Node(nX,nY,nextMove,nextTime));
                }
            }
        }
    }

    static class Node implements Comparable<Node>
    {
        int x;
        int y;
        int move;
        int time;

        public Node(int x, int y, int move, int time)
        {
            this.x = x;
            this.y = y;
            this.move = move;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
