package DP;

import java.io.*;
import java.util.*;

public class boj_15989
{
    static int test_case, n;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        int[][] d = new int[10001][4]; //dp[i][j] = j로 끝나는 수식으로 나타낸 i
        //d[n][1] = d[n-1][1]
        //d[n][2] = d[n-2][2] + d[n-2][2]
        //d[n][3] = d[n-3][1] + d[n-3][2] + d[n-3][3]
        d[1][1] = 1; // 1
        d[2][1] = 1; // 1+1
        d[2][2] = 1; // 2
        d[3][1] = 1; // 1+1+1
        d[3][2] = 1; // 1+2
        d[3][3] = 1; // 3
        for(int i=4; i<d.length; i++)
        {
            d[i][1] = d[i-1][1];
            d[i][2] = d[i-2][1] + d[i-2][2];
            d[i][3] = d[i-3][1] + d[i-3][2] + d[i-3][3];
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int sum = d[n][1] + d[n][2] + d[n][3];
            sb.append(sum).append('\n');
        }
        System.out.print(sb);
    }
}