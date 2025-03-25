import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n,t;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
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
        System.out.print(dijkstra());
    }

    static int dijkstra()
    {
        boolean[][][] visit = new boolean[n][n][3];
        visit[0][0][0] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0,0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curMove = cur.move;
            int curTime = cur.time;

            if(curX == n-1 && curY == n-1) return curTime;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nMove = curMove + 1;

                if(OOB(nX,nY) || visit[nX][nY][nMove % 3]) continue;

                int nTime = curTime + t;
                if(nMove == 3)
                {
                    nTime += map[nX][nY];
                    nMove = 0;
                }

                visit[nX][nY][nMove] = true;
                pq.add(new Node(nX,nY,nMove,nTime));
            }
        }
        return -1;
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
