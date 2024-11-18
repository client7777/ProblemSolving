package BFS_DFS;
//구슬 탈출4
import java.io.*;
import java.util.*;

public class boj_15653
{
    static int n,m;
    static char[][] map;
    static int holeX, holeY;
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
        visit = new boolean[n][m][n][m];

        int redX = 0, redY = 0, blueX = 0, blueY = 0;
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                char inputDat = str.charAt(j);
                map[i][j] = inputDat;
                if(inputDat == 'O')
                {
                    holeX = i;
                    holeY = j;
                }
                if(inputDat == 'R')
                {
                    redX = i;
                    redY = j;
                }
                if(inputDat == 'B')
                {
                    blueX = i;
                    blueY = j;
                }
            }
        }
        System.out.print(bfs(redX, redY, blueX, blueY));
    }
    //최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력, 빼내기가 불가능하다면 -1출력
    static int bfs(int redX, int redY, int blueX, int blueY)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{redX, redY, blueX, blueY, 1});
        visit[redX][redY][blueX][blueY] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curRedX = cur[0];
            int curRedY = cur[1];
            int curBlueX = cur[2];
            int curBlueY = cur[3];
            int curCnt = cur[4];

            for(int dir = 0; dir<4; dir++)
            {
                int nextRedX = curRedX;
                int nextRedY = curRedY;
                int nextBlueX = curBlueX;
                int nextBlueY = curBlueY;

                boolean isRedHole = false, isBlueHole = false;
                //해당 벡터 방향으로 벽을 만날 때까지 벽을 기울임
                while (map[nextRedX + dx[dir]][nextRedY + dy[dir]] != '#')
                {
                    nextRedX += dx[dir];
                    nextRedY += dy[dir];

                    if(nextRedX == holeX && nextRedY == holeY)
                    {
                        isRedHole = true;
                        break;
                    }
                }
                while (map[nextBlueX + dx[dir]][nextBlueY + dy[dir]] != '#')
                {
                    nextBlueX += dx[dir];
                    nextBlueY += dy[dir];

                    if(nextBlueX == holeX && nextBlueY == holeY)
                    {
                        isBlueHole = true;
                        break;
                    }
                }
                if(isBlueHole)
                {
                    //파란공만 구멍에 빠진 경우
                    continue;
                }
                if(!isBlueHole && isRedHole)
                {
                    //빨간 공만 구멍에 빠진 경우, 현재까지 기울인 횟수를 리턴
                    return curCnt;
                }
                //두 구슬 모두 구멍에 빠지지 않고, 빨간 구슬과 파란 구슬의 좌표가 겹친 경우
                if(nextRedX == nextBlueX && nextRedY == nextBlueY)
                {
                    switch (dir)
                    {   
                        //위로 기울일 경우
                        case 0:
                            if(curRedX > curBlueX) nextRedX -= dx[dir];
                            else nextBlueX -= dx[dir];
                            break;
                        //오른쪽으로 기울일 경우    
                        case 1:
                            if(curRedY < curBlueY) nextRedY -= dy[dir];
                            else nextBlueY -= dy[dir];
                            break;
                        //밑으로 기울일 경우    
                        case 2:
                            if(curRedX < curBlueX) nextRedX -= dx[dir];
                            else nextBlueX -= dx[dir];
                            break;
                        //왼쪽으로 기울일 경우    
                        case 3:
                            if(curRedY > curBlueY) nextRedY -= dy[dir];
                            else nextBlueY -= dy[dir];
                            break;
                    }
                }
                //다음에 탐색할 두 구슬의 좌표 조합이 결정되고, 그 좌표가 방문하지 않은 좌표라면
                if(!visit[nextRedX][nextRedY][nextBlueX][nextBlueY])
                {
                    //큐에 좌표를 저장, 횟수를 1누적
                    q.add(new int[]{nextRedX, nextRedY, nextBlueX, nextBlueY, curCnt + 1});
                    visit[nextRedX][nextRedY][nextBlueX][nextBlueY] = true;
                }
            }
        }
        return -1;
    }
}
