package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_20926
{
    static int w,h;
    static final int ROCK = -1, HOLE = -2, ESCAPE = -3;
    static int answer = Integer.MAX_VALUE;
    static int startX, startY;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];

        for(int i=0; i<h; i++)
        {
            String str = br.readLine();
            for(int j=0; j<w; j++)
            {
                int val = str.charAt(j);

                if(val == 'T')
                {
                    startX = i;
                    startY = j;
                }

                if(val == 'R') map[i][j] = ROCK;
                else if(val == 'E') map[i][j] = ESCAPE;
                else if(val == 'H') map[i][j] = HOLE;
                else map[i][j] = val - '0';
            }
        }

        bfs();
        System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs()
    {
        int[][] dist = new int[h][w];
        for(int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[startX][startY] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curTime = cur.time;

            if(curTime > dist[curX][curY]) continue;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX;
                int nY = curY;
                int nTime = curTime;

                while (true)
                {
                    nX += dx[dir];
                    nY += dy[dir];

                    if(OOB(nX,nY) || map[nX][nY] == HOLE) break;

                    if(map[nX][nY] == ROCK)
                    {
                        nX -= dx[dir];
                        nY -= dy[dir];

                        if(dist[nX][nY] > nTime)
                        {
                            dist[nX][nY] = nTime;
                            pq.add(new Node(nX,nY,nTime));
                        }

                        break;
                    }

                    if(map[nX][nY] == ESCAPE)
                    {
                        answer = Math.min(answer, nTime);
                        break;
                    }

                    nTime += map[nX][nY];
                }
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= h || y >= w;
    }

    static class Node implements Comparable<Node>
    {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time)
        {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Node o)
        {
            return Integer.compare(this.time, o.time);
        }
    }
}
