import java.util.*;

class Solution 
{
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] map) 
    {
        int n = map.length;
        int m = map[0].length;
        boolean[][] visit = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        visit[0][0] = true;
        q.add(new int[]{0,0,1});
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            if(curX == n-1 && curY == m-1)
            {
                return curDist;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(nX < 0 || nY < 0 || nX >= n || nY >= m) continue;
                if(visit[nX][nY] || map[nX][nY] == 0) continue;
                q.add(new int[]{nX, nY, curDist + 1});
                visit[nX][nY] = true;
            }
        }
        return -1; // 목표 지점에 도달할 수 없는 경우
    }
}
