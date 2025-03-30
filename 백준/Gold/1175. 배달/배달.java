import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int startX, startY;
    static char present = '1';
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S')
                {
                    startX = i;
                    startY = j;
                }

                if(map[i][j] == 'C')
                {
                    map[i][j] = present++;
                }
            }
        }
        
        System.out.print(bfs());
    }

    static int bfs()
    {
        boolean[][][][] visit = new boolean[n][m][1 << 2][4];
        for(int i=0; i<4; i++)
        {
            visit[startX][startY][0][i] = true;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0,0,-1));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;
            int curStatus = cur.status;
            int curDir = cur.dir;

            if(curStatus == (1 << 2) - 1) return curDist;

            for(int dir=0; dir<4; dir++)
            {
                if(curDir == dir) continue;

                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nextDist = curDist + 1;
                int nextStatus = curStatus;

                if(OOB(nX,nY) || visit[nX][nY][curStatus][dir] || map[nX][nY] == '#') continue;

                if(map[nX][nY] == '1' || map[nX][nY] == '2')
                {
                    nextStatus |= (1 << map[nX][nY] - '1');
                }

                visit[nX][nY][nextStatus][dir] = true;
                q.add(new Node(nX,nY,nextDist, nextStatus, dir));
            }
        }

        return -1;
    }

    static class Node
    {
        int x;
        int y;
        int dist;
        int status;
        int dir;

        public Node(int x, int y, int dist, int status, int dir)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.status = status;
            this.dir = dir;
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}