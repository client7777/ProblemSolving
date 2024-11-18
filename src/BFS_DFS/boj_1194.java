package BFS_DFS;
// 비트마스킹으로 각 열쇠에 대한 상태를 표현, 방문여부를 체크
import java.io.*;
import java.util.*;

public class boj_1194
{
    static int n,m;
    static char[][] map;
    static int startX, startY;
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
                if(map[i][j] == '0')
                {
                    startX = i;
                    startY = j;
                }
            }
        }
        bfs();
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0,0});
        boolean[][][] visit = new boolean[n][m][64]; // 6개의 열쇠로 나타낼 수 있는 모든 상태 = 2^6
        visit[startX][startY][0] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int keyState = cur[2];
            int curDist = cur[3];
            if(map[curX][curY] == '1')
            {
                System.out.print(curDist);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!OOB(nX,nY) && !visit[nX][nY][keyState] && map[nX][nY] != '#')
                {
                    if(map[nX][nY] >= 'a' && map[nX][nY] <= 'f')
                    {
                        int nextKeyState = keyState | (1 << (map[nX][nY] - 'a'));
                        if(!visit[nX][nY][keyState])
                        {
                            q.add(new int[]{nX,nY,nextKeyState,curDist + 1});
                            visit[nX][nY][nextKeyState] = true;
                        }
                    }
                    else if(map[nX][nY] >= 'A' && map[nX][nY] <= 'F')
                    {
                        //해당 열쇠가 존재하는지 검사
                        if((keyState & (1 <<(map[nX][nY] -'A'))) != 0)
                        {
                            visit[nX][nY][keyState] = true;
                            q.add(new int[]{nX,nY,keyState, curDist + 1});
                        }
                    }
                    else
                    {
                        q.add(new int[]{nX,nY,keyState, curDist + 1});
                        visit[nX][nY][keyState] = true;
                    }
                }
            }
        }
        System.out.print(-1);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
