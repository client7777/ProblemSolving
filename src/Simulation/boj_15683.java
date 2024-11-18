package Simulation;
//cctv 감시
import java.io.*;
import java.util.*;

public class boj_15683
{
    static int n,m; // 행, 열
    static int[][] map1; // 원본 배열
    static int[][] map2; // 시뮬레이션 배열
    static ArrayList<int[]> cctv = new ArrayList<>(); // cctv 좌표 저장
    static int[] dx = {-1,0,1,0}; // 방향벡터, 북 - 동 - 남 - 서
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map1 = new int[n][m]; // 원본
        map2 = new int[n][m]; // 시뮬레이션

        int minBlindSpot = 0; // 최소 사각지대

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map1[i][j] = Integer.parseInt(st.nextToken());
                if(map1[i][j] != 0 && map1[i][j] != 6) cctv.add(new int[]{i,j}); // cctv 좌표 저장
                if(map1[i][j] == 0 ) minBlindSpot++;
                //아직 감시 시작 전인 경우, 빈 칸인 경우 사각지대 개수 증가
            }
        }
        // 모든 경우의 수에 대한 완전탐색
        for(int tmp = 0; tmp < (1<<(2*cctv.size())); tmp++)
        {
            // 한가지 경우의 수가 끝났다면 원본 배열을 시뮬레이션 배열에 복사
            for(int i=0; i<n; i++) System.arraycopy(map1[i], 0, map2[i], 0, m);
            int brute = tmp;
            // 각 cctv에 대한 방향 설정
            for(int i=0; i<cctv.size(); i++)
            {
                int dir = brute%4; // 현재 ccvtv의 방향
                brute/=4; // 다음 cctv의 방향을 얻기 위해 4로 나눔(4진수이므로)
                int x = cctv.get(i)[0];
                int y = cctv.get(i)[1];

                //cctv 종류에 따른 분기문
                switch(map1[x][y])
                {
                    case 1:
                        upd(x,y,dir);
                        break;
                    case 2:
                        upd(x,y,dir);
                        upd(x,y,dir + 2);
                        break;
                    case 3:
                        upd(x,y,dir);
                        upd(x,y,dir+1);
                        break;
                    case 4:
                        upd(x,y,dir);
                        upd(x,y,dir+1);
                        upd(x,y,dir+2);
                        break;
                    case 5:
                        upd(x,y,dir);
                        upd(x,y,dir+1);
                        upd(x,y,dir+2);
                        upd(x,y,dir+3);
                        break;
                }
            }
            // 하나의 경우의 수에 대한 사각지대 계산
            int val = 0;
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<m; j++)
                {
                    if(map2[i][j] == 0) val++;
                }
            }
            minBlindSpot = Math.min(minBlindSpot, val); // 최소 사각지대 갱신
        }
        System.out.print(minBlindSpot);
    }
    // 감시 영역을 확장하는 함수
    static void upd(int x, int y, int dir)
    {
        dir = dir%4;
        // dir이 3이 넘어가는 경우가 발생하므로 dir을 매개변수로 받고 나머지 연산을 다시 해서 0~3의 값을 보장
        while(true)
        {
            x += dx[dir];
            y += dy[dir];
            // 경계 밖이거나 벽인 경우 감시 종료
            if(OOB(x,y) || map2[x][y] == 6) return;
            // cctv인 경우 무시
            if(map2[x][y] != 0 ) continue;
            // 감시 영역 마킹
            map2[x][y] = 7;
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
