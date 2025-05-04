package Graph;

import java.io.*;
import java.util.*;

public class boj_22255
{
    static final int INF = 987654321;
    static int n,m;
    static int startX, startY, endX, endY;
    static int[][] map;
    static int[][][] cost;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijkstra();
    }

    static void dijkstra()
    {
        cost = new int[n+1][m+1][3];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=m; j++)
            {
                Arrays.fill(cost[i][j], INF);
            }
        }

        cost[startX][startY][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, 1, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curStep = cur.step;
            int curDamage = cur.damage;

            if(curX == endX && curY == endY)
            {
                System.out.print(curDamage);
                return;
            }

            if(curDamage > cost[curX][curY][curStep]) continue;

            int mod = curStep % 3;

            int left = -1;
            int right = -1;

            switch (mod)
            {
                case 0 : left = 0; right = 4; break;
                case 1 : left = 0; right = 2; break;
                case 2 : left = 2; right = 4; break;
            }

            for(int dir=left; dir<right; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nStep = (curStep + 1) % 3;

                if(OOB(nX,nY) || map[nX][nY] == -1) continue;

                if(cost[nX][nY][nStep] > curDamage + map[nX][nY])
                {
                    cost[nX][nY][nStep] = curDamage + map[nX][nY];
                    pq.add(new Node(nX,nY,nStep, cost[nX][nY][nStep]));
                }
            }
        }

        System.out.print(-1);
    }

    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }

    static class Node implements Comparable<Node>
    {
        int x;
        int y;
        int step;
        int damage;

        public Node(int x, int y, int step, int damage)
        {
            this.x = x;
            this.y = y;
            this.step = step;
            this.damage = damage;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.damage, o.damage);
        }
    }
}
