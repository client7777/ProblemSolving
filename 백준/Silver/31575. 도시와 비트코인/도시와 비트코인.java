import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int[][] map;
    static int[] dx = {0,1};
    static int[] dy = {1,0};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    static void bfs()
    {
        boolean[][] visit = new boolean[m][n];
        visit[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            if(curX == m-1 && curY == n-1)
            {
                System.out.print("Yes");
                return;
            }

            for(int dir=0; dir<2; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;

                visit[nX][nY] = true;
                q.add(new Node(nX,nY));
            }
        }

        System.out.print("No");
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= m || y >= n;
    }

    static class Node
    {
        int x;
        int y;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

}
