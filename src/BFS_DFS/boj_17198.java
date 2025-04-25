package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_17198
{
    static int startX, startY, endX, endY;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] map = new char[10][10];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<10; i++)
        {
            String str = br.readLine();
            for(int j=0; j<10; j++)
            {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'B')
                {
                    startX = i;
                    startY = j;
                }

                if(map[i][j] == 'L')
                {
                    endX = i;
                    endY = j;
                }
            }
        }

        System.out.print(bfs());
    }

    static int bfs()
    {
        boolean[][] visit = new boolean[10][10];
        visit[startX][startY] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;

            if(curX == endX && curY == endY) return curDist - 1;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 'R') continue;

                visit[nX][nY] = true;
                q.add(new Node(nX,nY,curDist + 1));
            }
        }

        return -1;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= 10 || y >= 10;
    }

    static class Node
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
    }
}
