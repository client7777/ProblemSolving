package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_16197
{
    static int n,m;
    static char[][] map;
    static boolean[][][][] visit; //동전1, 동전2의 좌표 조합을 통해서 탐색 여부 체크
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<Integer> coin = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visit = new boolean[n][m][n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'o')
                {
                    coin.add(i);
                    coin.add(j);
                }
            }
        }
        System.out.print(bfs(coin.get(0),coin.get(1),coin.get(2),coin.get(3)));
    }
    static int bfs(int x,int y,int x1,int y1)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y,x1,y1,0});
        visit[x][y][x1][y1] = true;
        //큐에 두 동전의 좌표를 모두 넣고 동시에 탐색을 진행
        q.add(new int[]{x,y,x1,y1,0});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curX1 = cur[2];
            int curY1 = cur[3];
            int curCnt = cur[4];
            if(curCnt + 1 > 10) return -1;
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nX1 = curX1 + dx[dir];
                int nY1 = curY1 + dy[dir];
                //두 동전 모두 범위 밖으로 나갈 경우 무시
                if(OOB(nX,nY) && OOB(nX1, nY1)) continue;
                //하나만 범위 밖으로 나간 경우 값을 횟수를 리턴
                if(OOB(nX,nY) || OOB(nX1, nY1)) return curCnt + 1;

                //하나의 동전이 벽에 부딪혀서 막히더라도 다른 동전은 여전히 움직일 수 있다.
                //동전은 항상 일렬로 위치하지 않음
                //벽에 부딪힌 경우 제자리
                if(map[nX][nY] == '#')
                {
                    nX = curX;
                    nY = curY;
                }
                if(map[nX1][nY1] == '#')
                {
                    nX1 = curX1;
                    nY1 = curY1;
                }
                if(!visit[nX][nY][nX1][nY1])
                {
                    q.add(new int[]{nX,nY,nX1,nY1,curCnt + 1});
                    visit[nX][nY][nX1][nY1] = true;
                }
            }
        }
        return -1;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
