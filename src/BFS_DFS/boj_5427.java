package BFS_DFS;

import java.io.*;
import java.util.*;
//불
public class boj_5427
{
    static  int w,h;
    //방향 벡터
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] fire; // 불에 대한 BFS를 돌릴 배열
    static int[][] sang; // 상근이에 대한 BFS를 돌릴 배열
    static char[][] map; // 상근이가 탈출할 맵에 대한 배알
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine()); // 테스트케이스의 수
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<test_case; t++)
        {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); //맵의 너비
            h = Integer.parseInt(st.nextToken()); //맵의 높이
            map = new char[h][w];
            fire = new int[h][w];
            sang = new int[h][w];

            Queue<int[]> fireq = new LinkedList<>(); // 불에대한 큐
            Queue<int[]> sangq = new LinkedList<>(); // 상근이에 대한 큐
            for(int i=0; i<h; i++)
            {
                String str = br.readLine();
                for(int j=0; j<w; j++)
                {
                    map[i][j] = str.charAt(j);
                    fire[i][j] = -1; // 불에 대한 배열을 -1로 초기화
                    sang[i][j] = -1; // 상근이에 대한 배열을 -1로 초기화
                    //불의 위치가 입력되었다면
                    if(map[i][j] == '*')
                    {
                        fire[i][j] = 0; // 방문처리
                        fireq.add(new int[] {i,j}); // 큐에 좌포 저장
                    }
                    //상근이의 위치가 입력되었다면
                    if(map[i][j] == '@')
                    {
                        sang[i][j] = 0; // 방문처리
                        sangq.add(new int[] {i,j}); // 큐에 좌표 저장
                    }
                }
            }
            while(!fireq.isEmpty())
            {
                int[] cur = fireq.poll();
                int curX = cur[0];
                int curY = cur[1];

                for(int dir = 0; dir<4; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    //불이 도달할 수 없는 곳에대한 제약조건
                    //범위 밖이거나
                    if(OOB(nX,nY)) continue;
                    //이미 불길이 번진 곳이거나 벽인 곳은 방문하지 않는다.
                    if(fire[nX][nY] != -1 || map[nX][nY] == '#') continue;
                    fireq.add(new int[] {nX,nY});
                    fire[nX][nY] = fire[curX][curY] + 1;
                }
            }
            boolean escape = false; // boolean형 변수를 두어서 상근이의 탈출 여부를 체크
            while(!sangq.isEmpty() && !escape)
            {
                int[] cur = sangq.poll();
                int curX = cur[0];
                int curY = cur[1];
                for(int dir= 0; dir<4; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    if(OOB(nX,nY))
                    {
                        sb.append(sang[curX][curY] + 1).append('\n');
                        escape = true;
                        break;
                    }
                    if(sang[nX][nY] != -1 || map[nX][nY] == '#') continue;
                    // 상근이가 이동하려는 위치에 불이 도달했고 상근이가 그 위치에 도달하는 시간이 불이 도달하는 시간보다 늦으면 이동x
                    // 논리합으로 작성했을 경우 상근이가 불보다 빨리 도달하는 경우임에도 도달이 불가능하게 되는 경우가 발생
                    if(fire[nX][nY] != -1 && sang[curX][curY] + 1 >= fire[nX][nY]) continue;;
                    sangq.add(new int[] {nX,nY});
                    sang[nX][nY] = sang[curX][curY] + 1;
                }
            }
            if(!escape) // 상근이가 탈출에 성공하지 못했다면
            {
                sb.append("IMPOSSIBLE").append('\n');
            }
        }
        System.out.println(sb.toString());
    } // main method
    static  boolean OOB(int x, int y)
    {
        return x< 0 || y < 0 || x >= h || y >= w;
    } // OOB 함수

}// main class
