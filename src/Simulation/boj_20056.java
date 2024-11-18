package Simulation;

import java.io.*;
import java.util.*;

public class boj_20056
{
    static int n,m,k;
    static ArrayList<int[]>[][] map;
    static ArrayList<int[]> fireBall = new ArrayList<>();
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //격자의 행, 열
        m = Integer.parseInt(st.nextToken()); //파이어볼의 개수
        k = Integer.parseInt(st.nextToken()); // 이동 횟수
        map = new ArrayList[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                map[i][j] = new ArrayList<>();
            }
        }
        for(int i=0; i<m; i++)
        {
            // r,c에 질량이 m이고 속력과 방향이 s,d인 파이어볼이 존재
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int m = Integer.parseInt(st.nextToken()); // 질량
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 방향
            fireBall.add(new int[]{r,c,m,s,d});
        }
        for(int i=0; i<k; i++)
        {
            //k번 이동
            move();
            divide();
        }
        int ans = fireBallSum();
        System.out.print(ans);
    }
    static void move()
    {
        for (int[] cur : fireBall)
        {
            int curRow = cur[0];
            int curCal = cur[1];
            int curSpeed = cur[3];
            int curDir = cur[4];

            //1행과 n행은 연결되어있음, 격자 밖으로 나가면 연결되는 구조
            int nX = (curRow + n + dx[curDir] * (curSpeed % n)) % n;
            int nY = (curCal + n + dy[curDir] * (curSpeed % n)) % n;

            cur[0] = nX;
            cur[1] = nY;

            map[nX][nY].add(cur);
        }
    }

    static void divide()
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (map[i][j].size() < 2)
                {
                    map[i][j].clear();
                    continue;
                }

                int mSum = 0, sSum = 0, oddCnt = 0, evenCnt = 0;
                int size = map[i][j].size();
                List<int[]> toRemove = new ArrayList<>();

                for (int[] cur : map[i][j])
                {
                    mSum += cur[2];
                    sSum += cur[3];
                    if (cur[4] % 2 == 0) evenCnt++;
                    else oddCnt++;
                    toRemove.add(cur);
                }

                fireBall.removeAll(toRemove);
                map[i][j].clear();

                mSum /= 5;
                if (mSum == 0) continue;
                sSum /= size;

                if (oddCnt == 0 || evenCnt == 0)
                {
                    for (int k = 0; k < 8; k += 2)
                    {
                        fireBall.add(new int[]{i, j, mSum, sSum, k});
                    }
                }
                else
                {
                    for (int k = 1; k < 8; k += 2)
                    {
                        fireBall.add(new int[]{i, j, mSum, sSum, k});
                    }
                }
            }
        }
    }

    static int fireBallSum()
    {
        int sum = 0;
        for (int[] cur : fireBall)
        {
            sum += cur[2]; // Sum of mass (cur[2])
        }
        return sum;
    }

}
