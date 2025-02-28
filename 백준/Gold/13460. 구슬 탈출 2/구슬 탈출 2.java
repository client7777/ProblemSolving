import java.util.*;
import java.io.*;

public class Main
{
    static int n,m;
    static char[][] map;
    static boolean[][][][] visit;
    static int holeX, holeY;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1}; // 상우하좌 -> 시계방향으로 회전
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        map = new char[n][m];
        visit = new boolean[n][m][n][m];

        int redX = 0, redY = 0, blueX = 0, blueY = 0;


        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
                //구멍의 위치 저장
                if(map[i][j] == 'O')
                {
                    holeX = i;
                    holeY = j;
                }
                //파란 구슬의 초기 위치 저장
                else if(map[i][j] == 'B')
                {
                    blueX = i;
                    blueY = j;
                }
                //빨간 구슬의 초기 위치 저장
                else if(map[i][j] == 'R')
                {
                    redX = i;
                    redY = j;
                }
            }
        }
        System.out.print(bfs(redX, redY, blueX, blueY));
        br.close();
    }
    public static int bfs(int redX, int redY, int blueX, int blueY)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{redX, redY, blueX, blueY, 1});
        visit[redX][redY][blueX][blueY] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curRx = cur[0];
            int curRy = cur[1];
            int curBx = cur[2];
            int curBy = cur[3];
            int curCnt = cur[4];

            if(curCnt > 10)
            {
                return -1;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nextRx = curRx;
                int nextRy = curRy;
                int nextBx = curBx;
                int nextBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬 이동 -> 벽을 만날 때까지
                while (map[nextRx + dx[dir]][nextRy + dy[dir]] != '#')
                {
                    nextRx += dx[dir];
                    nextRy += dy[dir];
                    

                    if(nextRx == holeX && nextRy == holeY)
                    {
                        isRedHole = true;
                        break;
                    }
                }
            
                // 파란 구슬 이동 -> 벽을 만날 때까지
                while (map[nextBx + dx[dir]][nextBy + dy[dir]] != '#')
                {
                    nextBx += dx[dir];
                    nextBy += dy[dir];
    
                    //이동 중 구멍을 만날 경우
                    if(nextBx == holeX && nextBy == holeY)
                    {
                        isBlueHole = true;
                        break;
                    }
                }
                
                //파란 구슬이 구멍에 빠지면 실패
                if(isBlueHole)
                {
                    //해당 방향으로의 시도는 실패로 간주하고 다른 방향으로 기울여야 한다.
                    //continue를 사용하면 현재 방향만 무시하고 다음 방향으로 넘어가 계속 탐색을 이어감
                    //break를 사용하게 되면 다른 방향에 대한 탐색도 중단되어 성공할 수 있는 방향에 대한 탐색이 이루어지지 않음
                    continue;
                }

                //빨간 구슬이 구멍에 빠지고, 파란 구슬이 구멍에 빠지지 않았다면 성공
                if(isRedHole && !isBlueHole)
                {
                    return curCnt;
                }
                
                // 둘 다 구멍에 빠지지 않고 이동할 위치가 같은 경우 -> 위치 조정
                if(nextRx == nextBx && nextRy == nextBy)
                {
                    //위로 기울이기
                    if(dir == 0)
                    {
                        //빨간 구슬이 파란 구슬보다 아래쪽에 위치할 경우
                        //빨간 구슬이 나중에 도착했으므로 한 칸 아래로 되돌림
                        if(curRx > curBx) nextRx -= dx[dir];
                        else nextBx -= dx[dir];
                    }
                    //우로 기울이기
                    else if(dir == 1)
                    {
                        //파란 구슬이 빨간 구슬보다 오른쪽에 위치할 경우
                        //빨간 구슬이 나중에 도착했으므로 한 칸 왼쪽으로 되돌림
                        if(curRy < curBy) nextRy -= dy[dir];
                        else nextBy -= dy[dir];
                    }
                    //하로 기울이기
                    else if(dir == 2)
                    {
                        //파란 구슬이 빨간 구슬보다 아래 위치할 경우
                        //빨간 구슬이 나중에 도착했으므로 한 칸 위로 되돌림
                        if(curRx < curBx) nextRx -= dx[dir];
                        else nextBx -= dx[dir];
                    }
                    //좌로 기울이기
                    else
                    {
                        //빨간 구슬이 파란 구슬보다 오른쪽에 위치할 경우
                        //빨간 구슬이 나중에 도착했으므로 한 칸 오른쪽으로 되돌림
                        if(curRy > curBy) nextRy -= dy[dir];
                        else nextBy -= dy[dir];
                    }
                }
                //현재 위치 조합이 이미 탐색되었는지 확인하는 4차원 방문 배열
                //빨간 구슬과 파란 구슬의 위치 조합이 같은 경우 다시 탐색하는 것을 방지
                if(!visit[nextRx][nextRy][nextBx][nextBy])
                {
                    visit[nextRx][nextRy][nextBx][nextBy] = true;
                    q.add(new int[]{nextRx, nextRy, nextBx, nextBy, curCnt + 1});
                }
            }
        }
        return -1;
    }
}
