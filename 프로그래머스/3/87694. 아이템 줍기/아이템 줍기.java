import java.util.*;

class Solution 
{
    static int[][] map = new int[101][101];
    static int[] dx = {-1,0,1,0}; 
    static int[] dy = {0,1,0,-1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) 
    {
        for (int[] row : rectangle) 
        {
            mark(row[0] * 2, row[1] * 2, row[2] * 2, row[3] * 2);
        }

        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }

    static int bfs(int startX, int startY, int endX, int endY) 
    {
        boolean[][] visit = new boolean[101][101];
        visit[startX][startY] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            if (curX == endX && curY == endY) 
            {
                return curDist / 2;
            }

            for (int dir = 0; dir < 4; dir++) 
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                //배열의 범위 밖이거 테두리가 아니라면 이동 불가
                if (OOB(nX, nY)) continue;
                if (visit[nX][nY] || map[nX][nY] != 1) continue;

                q.add(new int[]{nX, nY, curDist + 1});
                visit[nX][nY] = true;
            }
        }
        return 0;
    }

    static boolean OOB(int x, int y) 
    {
        return x < 0 || y < 0 || x > 100 || y > 100;
    }

    static void mark(int x1, int y1, int x2, int y2) 
    {
        for (int i = x1; i <= x2; i++) 
        {
            for (int j = y1; j <= y2; j++) 
            {
                if (map[i][j] == 2) continue; // 이미 내부면 넘어감

                if (i == x1 || i == x2 || j == y1 || j == y2) 
                    map[i][j] = 1; // 테두리
                else 
                    map[i][j] = 2; // 내부
            }
        }
    }
}