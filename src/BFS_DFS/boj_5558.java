package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_5558
{
    static int h,w,n;
    static int startX, startY;
    static char[][] map;
    static boolean[][][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[h][w];

        for(int i=0; i<h; i++)
        {
            String str = br.readLine();
            for(int j=0; j<w; j++)
            {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S')
                {
                    startX = i;
                    startY = j;
                }
            }
        }

        visit = new boolean[h][w][11];

        System.out.print(bfs());
    }

    static int bfs()
    {
        visit[startX][startY][1] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0, 1));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;
            int curLevel = cur.level;

            //n개의 치즈를 모두 먹었으면 거리 반환
            if(curLevel > n) return curDist;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY][curLevel] || map[nX][nY] == 'X') continue;

                //치즈를 먹지 않는 경우
                visit[nX][nY][curLevel] = true;
                q.add(new Node(nX,nY,curDist + 1, curLevel));

                //치즈를 먹는 경우
                if(map[nX][nY] >= '1' && map[nX][nY] <= '9' && map[nX][nY] - '0' == curLevel)
                {
                    if(visit[nX][nY][curLevel + 1]) continue;
                    visit[nX][nY][curLevel + 1] = true;
                    q.add(new Node(nX,nY,curDist + 1, curLevel + 1));
                }
            }
        }

        return -1;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= h || y >= w;
    }

    static class Node
    {
        int x;
        int y;
        int dist;
        int level;

        public Node(int x, int y, int dist, int level)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.level = level;
        }
    }
}

//레벨이 1인 치즈부터 n인 치즈까지 순서대로 먹음