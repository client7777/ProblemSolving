package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1309
{
    static final int MOD = 9901;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] d = new int[n+1];

        d[1] = 3;

        if(n == 1)
        {
            System.out.print(d[1]);
            return;
        }

        d[2] = 7;

        for(int i=3; i<=n; i++)
        {
            d[i] = (d[i-1] * 2 + d[i-2]) % MOD;
        }

        System.out.print(d[n]);
    }
}
