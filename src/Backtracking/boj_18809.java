package Backtracking;
//Gaaaaaaaaaaaarden
import java.io.*;
import java.util.*;

public class boj_18809 {
    static int n, m, r, g;
    static int[][] map;
    static ArrayList<int[]> land = new ArrayList<>();
    static ArrayList<int[]> red = new ArrayList<>();
    static ArrayList<int[]> green = new ArrayList<>();
    static boolean[] selected;
    static int max = 0; // 결과값을 저장하는 변수
    static final int RED = 3;
    static final int GREEN = 4;
    static final int FLOWER = 5;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    land.add(new int[]{i, j}); // 배양지 추가
                }
            }
        }
        selected = new boolean[land.size()];
        combGreen(0, 0); // 초록색 비료 조합 선택
        System.out.println(max); // 최대 꽃 수 출력
    }

    static void combGreen(int at, int depth) 
    {
        //초록색 배양액을 뿌릴 좌표를 모두 정했다면
        if (depth == g) 
        {
            //빨간색 배양액을 뿌릴 좌표를 정하러 감
            combRed(0, 0);
            return;
        }
        for (int i = at; i < land.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                green.add(land.get(i));
                combGreen(i + 1, depth + 1);
                selected[i] = false;
                green.remove(green.size() - 1);
            }
        }
    }

    static void combRed(int at, int depth) 
    {
        //빨간색 배양액을 뿌릴 좌표를 모두 골랐다면
        if (depth == r) 
        {
            //배양액을 뿌리러 감
            bfs();
            return;
        }
        for (int i = at; i < land.size(); i++)
        {
            if (!selected[i] && !green.contains(land.get(i)))
            {
                selected[i] = true;
                red.add(land.get(i));
                combRed(i + 1, depth + 1);
                selected[i] = false;
                red.remove(red.size() - 1);
            }
        }
    }

    private static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        int[][] state = new int[n][m];
        int[][] time = new int[n][m];

        // 선택한 배양지에 비료 놓기
        for (int[] p : red)
        {
            state[p[0]][p[1]] = RED;
            q.offer(new int[]{p[0], p[1], 0, RED});
        }
        for (int[] p : green)
        {
            state[p[0]][p[1]] = GREEN;
            q.offer(new int[]{p[0], p[1], 0, GREEN});
        }
        int sum = 0;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curTime = cur[2];
            int curType = cur[3];

            if (state[curX][curY] == FLOWER) continue;

            for (int dir = 0; dir < 4; dir++) 
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;
                if (map[nX][nY] != 0)
                {
                    // 아직 배양액이 퍼지지 않았다면 퍼트림
                    if (state[nX][nY] == 0) 
                    {
                        state[nX][nY] = curType; // 현재 배양액 색깔로 상태 갱신
                        time[nX][nY] = curTime + 1; // 시간 증가
                        q.offer(new int[]{nX, nY, curTime + 1, curType});
                    }
                    // 빨간색이 있을 때 초록색이 퍼지는 경우
                    else if (state[nX][nY] == RED && curType == GREEN && time[nX][nY] == curTime + 1)
                    {
                        sum++;
                        state[nX][nY] = FLOWER; // 꽃이 핌
                    }
                    // 초록색이 있을 때 빨간색이 퍼지는 경우
                    else if (state[nX][nY] == GREEN && curType == RED && time[nX][nY] == curTime + 1)
                    {
                        sum++;
                        state[nX][nY] = FLOWER; // 꽃으로 변환
                    }
                }
            }
        }
        max = Math.max(max, sum);
    }

    private static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
