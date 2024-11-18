package DP;

import java.io.*;
import java.util.*;

public class boj_15993
{
    static int test_case;
    static final int DIVIDE = 1_000_000_009;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());
        //d[i][0] = i를 짝수개로 나타내는 방법의 수, d[i][1] = i를 홀수개로 나타내는 방법의 수
        long[][] d = new long[100001][2];
        d[1][0] = 1;
        d[1][1] = 0;
        d[2][0] = 1;
        d[2][1] = 1;
        d[3][0] = 2;
        d[3][1] = 2;
        for(int i=4; i<d.length; i++)
        {
            d[i][0] = (d[i-1][1] + d[i-2][1] + d[i-3][1]) % DIVIDE;;
            d[i][1] = (d[i-1][0] + d[i-2][0] + d[i-3][0]) % DIVIDE;;
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sb.append((d[n][0] % DIVIDE) + " " + (d[n][1] % DIVIDE)).append('\n');
        }
        System.out.print(sb);
    }
}