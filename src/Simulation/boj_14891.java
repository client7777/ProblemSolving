package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14891
{
    static int[][] gear;
    static int[] d;
    static int n,m;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gear = new int[4][8];

        for(int i=0; i<4; i++)
        {
            String str = br.readLine();
            for(int j=0; j<8; j++)
            {
                gear[i][j] = str.charAt(j) - '0';
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        while (k-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            //회전시킬 바퀴의 번호
            int wheelNum = Integer.parseInt(st.nextToken()) -1;
            //방향
            int dir = Integer.parseInt(st.nextToken());

            d = new int[4];
            d[wheelNum] = dir;
            checkDir(wheelNum);
            gearTurn();
        }
        int ans = 0;
        for(int i=0; i<4; i++)
        {
            if(gear[i][0] == 1)
            {
                ans += 1 << i;
            }
        }
        System.out.print(ans);
    }
    //옆 바퀴와 맞닿은 극이 다르다면 반대 방향으로 회전
    static void checkDir(int num)
    {
        // 좌측 톱니 확인
        for (int i = num - 1; i >= 0; i--)
        {
            if (gear[i][2] != gear[i + 1][6]) d[i] = -d[i + 1]; // 서로 다른 극일 때 회전
            else break; // 회전하지 않으면 종료
        }
        // 우측 톱니 확인
        for (int i = num + 1; i < 4; i++)
        {
            if (gear[i][6] != gear[i - 1][2])d[i] = -d[i - 1]; // 서로 다른 극일 때 회전
            else break; // 회전하지 않으면 종료
        }
    }

    static void gearTurn()
    {
        int temp = 0;
        for(int i=0; i<4; i++)
        {
            //시계 방향으로 회전하는 경우
            if(d[i] == 1)
            {
                temp = gear[i][7];
                for(int j=7; j>0; j--)
                {
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = temp;
            }
            //반시계 방향으로 회전하는 경우
            else if(d[i] == -1)
            {
                temp = gear[i][0];
                for(int j=0; j<7; j++)
                {
                    gear[i][j] = gear[i][j+1];
                }
                gear[i][7] =temp;
            }
        }
    }
}
