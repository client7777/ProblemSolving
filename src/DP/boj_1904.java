package DP;

import java.io.*;

public class boj_1904
{
    static final int MOD = 15746;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 1)
        {
            System.out.print(1);
            return;
        }
        int[] d = new int[n+1];
        d[1] = 1;
        d[2] = 2;
        for(int i=3; i<=n; i++)
        {
            d[i] = (d[i-1] + d[i-2]) % MOD;
        }
        System.out.print(d[n]);
    }
}
