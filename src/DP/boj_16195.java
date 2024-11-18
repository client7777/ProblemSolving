package DP;

import java.io.*;
import java.util.*;

public class boj_16195
{
    static int test_case;
    static final int DIVIDE = 1_000_000_009;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        long[][] d = new long[1001][1001]; //d[i][j] = i를 j개의 숫자를 사용해서 나타낸 방법의 수
        d[1][1] = 1;
        d[2][1] = 1;
        d[2][2] = 1;
        d[3][1] = 1;
        d[3][2] = 2;
        d[3][3] = 1;
        for(int i=4; i<d.length; i++)
        {
            for(int j=2; j<i; j++)
            {
                d[i][j] = (d[i-1][j-1] + d[i-2][j-1] + d[i-3][j-1]) % DIVIDE;
            }
            d[i][i] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long sum = 0;
            for(int i=1; i<=m; i++)
            {
                sum = (sum + d[n][i]) % DIVIDE;
            }
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
