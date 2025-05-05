package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_25416
{
    static int[][] map = new int[5][5];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5; i++)
        {
            String[] row = br.readLine().split(" ");
            for(int j=0; j<5; j++)
            {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        String[] position = br.readLine().split(" ");
        int startX = Integer.parseInt(position[0]);
        int startY = Integer.parseInt(position[1]);

        System.out.print(bfs(startX, startY));
    }

    static int bfs(int startX, int startY)
    {
        boolean[][] visit = new boolean[5][5];
        visit[startX][startY] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curMove = cur.move;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == -1) continue;

                if(map[nX][nY] == 1)
                {
                    return curMove + 1;
                }

                visit[nX][nY] = true;
                q.add(new Node(nX,nY,curMove + 1));
            }
        }

        return -1;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= 5 || y >= 5;
    }

    static class Node
    {
        int x;
        int y;
        int move;

        public Node(int x, int y, int move)
        {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
