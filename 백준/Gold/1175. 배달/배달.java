import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static char destination = '1';
    static int startX, startY;
    static char[][] map;
    static boolean[][][][] visit;
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
                    map[i][j] = destination++;
                }
            }
        }

        visit = new boolean[n][m][3][4];

        System.out.print(bfs());
    }

    static int bfs()
    {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0, 0, -1));

        for(int dir=0; dir<4; dir++)
        {
            visit[startX][startY][0][dir] = true;
        }

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;
            int curStatus = cur.status;
            int curDir = cur.dir;

            if (map[curX][curY] == '1' || map[curX][curY] == '2')
            {
                curStatus |= map[curX][curY] - '0';
            }

            if(curStatus == 3) return curDist;

            for(int dir=0; dir<4; dir++)
            {
                if(curDir == dir) continue; // 같은 방향으로 연속해서 진행 불가
                
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY][curStatus][dir] || map[nX][nY] == '#') continue;

                q.add(new Node(nX,nY, curDist + 1, curStatus, dir));
                visit[nX][nY][curStatus][dir] = true;
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
