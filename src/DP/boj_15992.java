package DP;

import java.io.*;
import java.util.*;
// 0 < m <= n <= 1000
// 1,2,3의 숫자 중에 m개를 사용해서 n을 1,2,3의 합으로 표현
public class boj_15992
{
    static int test_case,n,m;
    static final int DIVIDE = 1_000_000_009;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        long[][] d = new long[1001][1001]; //d[i][j] = i를 j개의 합으로 표현한 방법의 수
        d[1][1] = 1;
        d[2][1] = 1;
        d[2][2] = 1;
        d[3][1] = 1;
        d[3][2] = 2;
        d[3][3] = 1;

        for(int i=4; i<d.length; i++)
        {
            // i가 4부터 시작하는 상황에서 1개의 숫자로 4이상의 숫자를 나타낼 방법은 없음
            for(int j=2; j < i; j++)
            {
                d[i][j] = (d[i-1][j-1] + d[i-2][j-1] + d[i-3][j-1]) % DIVIDE;
            }
            d[i][i] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            sb.append(d[n][m]%DIVIDE).append('\n');
        }
        System.out.print(sb);
    }
}
/*
d[1][1] = 1 1

d[2][1] = 1 2
d[2][2] = 1 1+1

d[3][1] = 1 3
d[3][2] = 2 1+2 2+1
d[3][3] = 1 1+1+1

d[4][1] = 0
d[4][2] = 3 1+3 3+1 2+2
d[4][3] = 3 1+1+2 1+2+1 2+1+1
d[4][4] = 1 1+1+1+1

d[5][1] = 0
d[5][2] = 2 2+3 3+2
d[5][3] = 6 1+1+3 1+3+1 3+1+1 2+2+1 1+2+2 2+1+2
d[5][4] = 4 1+1+1+2 1+1+2+1 1+2+1+1 2+1+1+1
d[5][5] = 1 1+1+1+1+1+1

d[6][1] = 0
d[6][2] = 1 3+3
d[6][3] = 7 2+2+2 1+2+3 1+3+2 2+1+3 2+3+1 3+1+2 3+2+1
d[6][4] = 4 1+1+1+3 1+1+3+1 1+3+1+1 3+1+1+1
d[6][5] = 5 1+1+1+1+2 1+1+1+2+1 1+1+2+1+1 1+2+1+1+1 2+1+1+1+1
d[6][6] = 1 1+1+1+1+1+1
*/