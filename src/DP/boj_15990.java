package DP;

import java.io.*;
import java.util.*;

public class boj_15990
{
    static int test_case, n;
    static final int DIVIDE =  1_000_000_009;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        long[][] d = new long[100001][4];

        d[1][1] = 1; // 1
        d[2][1] = 0;
        d[2][2] = 1; // 2
        d[3][1] = 1; // 2+1
        d[3][2] = 1; // 1+2
        d[3][3] = 1; // 3

        for(int i=4; i<d.length; i++)
        {
            d[i][1] = (d[i-1][2] + d[i-1][3]) % DIVIDE;
            d[i][2] = (d[i-2][3] + d[i-2][1]) % DIVIDE;
            d[i][3] = (d[i-3][2] + d[i-3][1]) % DIVIDE;
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            long sum = (d[n][1] + d[n][2] + d[n][3]) % DIVIDE;
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
    }
}
/*
d[1][1] = 1     1

d[2][1] = 0
d[2][2] = 1     2

d[3][1] = 1     2+1
d[3][2] = 1     1+2
D[3][3] = 1     3

d[4][1] = 2     1+2+1, 3+1
d[4][2] = 0
d[4][3] = 1     1+3

d[5][1] =1      1+3+1
d[5][2] =1      2+1+2
d[5][3] =1      2+3

d[6][1] = 1      2+1+2+1 2+3+1  d[n][1] = d[n-1][2] + d[n-1][3]
d[6][2] = 1      1+3+2 3+1+2    d[n][2] = d[n-2][3] + d[n-2][1]
d[6][3] = 1      1+2+3 2+1+3    d[n][3] = d[n-3][2] + d[n-3][1]
*/