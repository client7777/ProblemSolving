package BFS_DFS;
//구슬 탈출
import java.util.*;
import java.io.*;

public class boj_13459
{
    static int n,m;
    static int holeX, holeY; // 구멍의 x,y 좌표
    static char[][] map;
    static boolean[][][][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1}; // 방향벡터 -> 상우하좌
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visit = new boolean[n][m][n][m];

        int redX = 0, redY = 0, blueX = 0, blueY = 0; // 빨간 구슬과 파란 구슬의 x,y 좌표를 저장할 변수
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'O')
                {
                    holeX = i;
                    holeY = j;
                }
                if(map[i][j] == 'R')
                {
                    redX = i;
                    redY = j;
                }
                if(map[i][j] == 'B')
                {
                    blueX = i;
                    blueY = j;
                }
            }
        }
        System.out.print(bfs(redX, redY, blueX, blueY));
    }
    //파란 구슬을 구멍에 넣지 않으면서 빨간 구슬을 10번 이하로 움직여서 빼낼 수 있으면 1을 없으면 0을 출력한다.
    static int bfs(int redX, int redY, int blueX, int blueY)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{redX, redY, blueX, blueY, 1});
        visit[redX][redY][blueX][blueY] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curRedx  = cur[0];
            int curRedy  = cur[1];
            int curBluex = cur[2];
            int curBluey = cur[3];
            int curCnt   = cur[4];
            //기울인 횟수가 10회를 초과한 경우
            if(curCnt > 10)
            {
                return 0;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nextRedx = curRedx;
                int nextRedy = curRedy;
                int nextBluex = curBluex;
                int nextBluey = curBluey;

                boolean isRedHole = false;
                boolean isBlueHole = false;
                //두 구슬을 각각 벡테방향으로 벽을 만날 때까지 기울임
                while (map[nextRedx + dx[dir]][nextRedy + dy[dir]] != '#')
                {
                    nextRedx += dx[dir];
                    nextRedy += dy[dir];

                    //만약 빨간 구슬이 구멍에 빠지면
                    if(nextRedx == holeX && nextRedy == holeY)
                    {
                        isRedHole = true;
                        break;
                    }
                }
                while (map[nextBluex + dx[dir]][nextBluey + dy[dir]] != '#')
                {
                    nextBluex += dx[dir];
                    nextBluey += dy[dir];

                    if(nextBluex == holeX && nextBluey == holeY)
                    {
                        isBlueHole = true;
                        break;
                    }
                }
                //파란공만 구멍에 빠진 경우 실패한 경우이므로
                if(isBlueHole)
                {
                    //다른 방향으로 기울이기 위해 현재 방향은 탐색하지 않고 넘김
                    continue;
                }
                if(!isBlueHole && isRedHole)
                {
                    return 1;
                }
                //두 구슬 모두 구멍에 빠지지 않고 좌표가 겹치게 된 경우
                if(nextRedx == nextBluex && nextRedy == nextBluey)
                {
                    // 상
                    if(dir == 0)
                    {
                        //빨간 구슬이 파란 구슬보다 밑에 위치했을 경우, 빨간 구슬이 늦게 도착했으므로 좌표 조정
                        if(curRedx > curBluex) nextRedx -= dx[dir];
                        else nextBluex -= dx[dir];
                    }
                    // 우
                    else if(dir == 1)
                    {
                        //빨간 구슬이 파란 구슬보다 왼쪽에 위치했을 경우
                        if(curRedy < curBluey) nextRedy -= dy[dir];
                        else nextBluey -= dy[dir];
                    }
                    // 하
                    else if(dir == 2)
                    {
                        //빨간 구슬이 파란 구슬보다 위에 위치했을 경우
                        if(curRedx < curBluex) nextRedx -= dx[dir];
                        else nextBluex -= dx[dir];
                    }
                    // 좌
                    else
                    {
                        //빨간 구슬이 파란 구슬보다 오른쪽에 위치했을 경우
                        if(curRedy > curBluey) nextRedy -= dy[dir];
                        else nextBluey -= dy[dir];
                    }
                }
                if(!visit[nextRedx][nextRedy][nextBluex][nextBluey])
                {
                    q.add(new int[]{nextRedx, nextRedy, nextBluex, nextBluey, curCnt + 1});
                    visit[nextRedx][nextRedy][nextBluex][nextBluey] = true;
                }
            }
        }
        return 0;
    }
}
