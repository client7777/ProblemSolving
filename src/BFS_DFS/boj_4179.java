package BFS_DFS;
//지훈이와 불의 BFS를 따로 돌려서 지훈이가 갈 수 있는 지점을 체크
//지훈이가 맵의 범위를 벗어난다면 탈출 성공
import java.io.*;
import java.util.*;

public class boj_4179
{
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static  char[][] map;
    static  int r,c; // r은 행, c는 열
    static int[][] jihoon, fire;

    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        jihoon = new int[r][c];
        fire = new int[r][c];

        Queue<int[]> fireq = new LinkedList<>();
        Queue<int[]> jihoonq = new LinkedList<>();

        for(int i=0; i<r; i++)
        {
            String str = br.readLine();
            for(int j=0; j<c; j++)
            {
                map[i][j] = str.charAt(j);
                fire[i][j] = -1; // 불의 BFS를 위한 배열의 값을 -1로 초기화, -1은 방문하지 않은 상태
                jihoon[i][j] = -1; // 불과 마찬가지
                // map의 배열의 값이 J라면 지훈이의 위치이므로
                if(map[i][j] == 'J')
                {
                    jihoonq.add(new int[] {i,j}); // 지훈이의 위치를 지훈이의 큐에 저장 , BFS 탐색 시작
                    jihoon[i][j] = 0; // 지훈이의 위치는 값을 0으로 초기화, 방문 여부와 거리 체크를 위함
                }
                if(map[i][j] == 'F')
                {
                    fireq.add(new int[]{i,j}); // 불의 탐색 시작점을 큐에 저장
                    fire[i][j] = 0; // 불의 시작점은 0으로 초기화, 방문처리
                }
            }
        }
        //불에대한 BFS 먼저 수행
        while(!fireq.isEmpty())
        {
            int[] cur = fireq.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX, nY)) continue;
                if(map[nX][nY] == '#' || fire[nX][nY] != -1) continue;
                fire[nX][nY] = fire[curX][curY] + 1;
                fireq.add(new int[] {nX,nY});
            }
        }
        // 지훈이에 대한 BFS
        while(!jihoonq.isEmpty())
        {
            int[] cur = jihoonq.poll();
            int curX = cur[0];
            int curY = cur[1];

            //만약 지훈이가 맵의 범위를 벗어난다면 
            //불을 피해서 탈출을 성공했다는 의미

            for(int dir = 0;dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX, nY))
                {
                    // 지훈의 배열값에는 curX, curY 지점까지 가는 시간이 저장되어 있으므로
                    // 탈출을 하려면 현재 지점에서 1의 시간만큼 더 이동해야 하므로 현재 위치값에 1을 더해줌
                    // 탈출에 성공하는 순간은 현재 위치에서 한 칸 더 이동할 때이므로 1을 더해줌
                    System.out.println(jihoon[curX][curY] + 1);
                    return;
                }
                if(jihoon[nX][nY] >= 0 || map[nX][nY] == '#') continue;;
                //지훈이는 불이 이미 도달한 곳이거나 지훈이가 도착할 시간보다 빠르거나 같은 곳은 방문 불가
                if(fire[nX][nY] != -1 && fire[nX][nY] <= jihoon[curX][curY] + 1) continue;;
                jihoon[nX][nY] = jihoon[curX][curY] + 1;
                jihoonq.add(new int[]{nX, nY});
            }
        }
        // 탐색이 끝났는데도 탈출을 하지 못했다면 출력
        System.out.println("IMPOSSIBLE ");
    }
    static  boolean OOB(int x, int y)
    {
        return x< 0 || y < 0 || x >= r || y >= c;
    }
}
