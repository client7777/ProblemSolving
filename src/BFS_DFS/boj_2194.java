package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2194
{
    static int n,m,a,b;
    static int startX, startY, endX, endY;
    static boolean[][] visit;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visit = new boolean[n+1][m+1];
        map = new int[n+1][m+1];

        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        System.out.println(bfs());

    }

    static int bfs()
    {
        visit[startX][startY] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;

            if(curX == endX && curY == endY) return curDist;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1) continue;

                boolean flag = true;

                for(int i=nX; i<=nX + a - 1; i++)
                {
                    if(!flag) break;

                    for(int j=nY; j<=nY + b - 1; j++)
                    {
                        if(OOB(i,j) || map[i][j] == 1)
                        {
                            flag = false;
                            break;
                        }
                    }
                }

                if(flag)
                {
                    visit[nX][nY] = true;
                    q.add(new Node(nX,nY,curDist + 1));
                }
            }
        }

        return -1;
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
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
