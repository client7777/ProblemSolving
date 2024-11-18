package DP;

import java.io.*;
import java.util.*;

public class boj_15988
{
    static int test_case;
    static long[] d = new long[1000001];
    static final int DIVIDE = 1_000_000_009;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        d[1] = 1;
        d[2] = 2;
        d[3] = 4;

        for(int i=4; i<d.length; i++)
        {
            d[i] = (d[i-1] + d[i-2] + d[i-3]) % DIVIDE;
        }
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sb.append(d[n]).append('\n');
        }
        System.out.print(sb);
    }
}
