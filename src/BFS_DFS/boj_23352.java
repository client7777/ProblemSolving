package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_23352
{
    static int n,m;
    static int maxMoveDistance = 0;
    static int[][] map;
    static int password = 0;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] != 0)
                {
                    bfs(i,j);
                }
            }
        }

        System.out.print(password);
    }

    static void bfs(int x, int y)
    {
        int start = map[x][y];

        boolean[][] visit = new boolean[n][m];
        visit[x][y] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curMove = cur.move;
            int end = map[curX][curY];

            if(curMove > maxMoveDistance)
            {
                password = start + end;
                maxMoveDistance = curMove;
            }
            else if(curMove == maxMoveDistance)
            {
                password = Math.max(password, (start + end));
            }

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nMove = curMove + 1;

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;
                visit[nX][nY] = true;
                q.add(new Node(nX,nY,nMove));
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
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