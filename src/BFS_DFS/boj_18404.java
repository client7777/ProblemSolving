package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_18404
{
    static int n;
    static int nightX, nightY;
    static int[][] visit;
    static int[] dx = {-2,-2,-1,-1,1,1,2,2};
    static int[] dy = {-1,1,-2,2,-2,2,-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nightX = Integer.parseInt(st.nextToken());
        nightY = Integer.parseInt(st.nextToken());

        visit = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                visit[i][j] = -1;
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(visit[x][y]).append(" ");
        }

        System.out.print(sb);
    }

    static void bfs()
    {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(nightX, nightY, 0));

        visit[nightX][nightY] = 0;

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;

            for(int dir=0; dir<8; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] != -1) continue;

                visit[nX][nY] = visit[curX][curY] + 1;
                q.add(new Node(nX,nY,curDist + 1));
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > n;
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
