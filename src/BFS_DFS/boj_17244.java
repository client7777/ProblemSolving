package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_17244
{
    static int m,n;
    static char x = '0';
    static int startX, startY;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

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

                if(map[i][j] == 'X')
                    map[i][j] = x++;
            }
        }

        System.out.print(bfs());
    }

    static int bfs()
    {
        boolean[][][] visit = new boolean[n][m][1 << 5]; // 물건의 개수는 최대 5개
        visit[startX][startY][0] = true;

        ArrayDeque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(startX, startY, 0, 0));

        while (!dq.isEmpty())
        {
            Node cur = dq.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;
            int curState = cur.state;

            if(map[curX][curY] == 'E' && curState == (1 << x - '0') - 1) return curDist;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nextState = curState;

                if(OOB(nX,nY) || visit[nX][nY][curState] || map[nX][nY] == '#') continue;

                if(map[nX][nY] >= '0' && map[nX][nY] <= '4')
                {
                    nextState |= (1 << map[nX][nY] - '0');
                }

                visit[nX][nY][nextState] = true;
                dq.add(new Node(nX,nY,curDist + 1, nextState));
            }
        }

        return -1;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }

    static class Node
    {
        int x;
        int y;
        int dist;
        int state;

        public Node(int x, int y, int dist, int state)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.state = state;
        }
    }
}
