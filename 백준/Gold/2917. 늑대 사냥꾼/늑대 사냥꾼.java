import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n,m;
    static int[][] dist;
    static char[][] map;
    static int startX, startY, endX, endY;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n][m];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(dist[i], -1);
        }

        map = new char[n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);

                if(map[i][j] == '+')
                {
                    dist[i][j] = 0;
                    q.add(new Node(i,j));
                }

                if(map[i][j] == 'V')
                {
                    startX = i;
                    startY = j;
                }

                if(map[i][j] == 'J')
                {
                    endX = i;
                    endY = j;
                }
            }
        }

        treeDist();

        dijkstra();

    }

    static void treeDist()
    {
        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || dist[nX][nY] != -1) continue;

                dist[nX][nY] = dist[curX][curY] + 1;
                q.add(new Node(nX,nY));
            }
        }
    }

    static void dijkstra()
    {
        int minDist = dist[startX][startY];

        boolean[][] visit = new boolean[n][m];
        visit[startX][startY] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, dist[startX][startY]));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;

            minDist = Math.min(minDist, curDist);

            if(map[curX][curY] == 'J')
            {
                System.out.print(minDist);
                return;
            }

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY]) continue;

                visit[nX][nY] = true;
                pq.add(new Node(nX,nY,dist[nX][nY]));
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }

    static class Node implements Comparable<Node>
    {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o)
        {
            return Integer.compare(o.dist, this.dist);
        }
    }
}
