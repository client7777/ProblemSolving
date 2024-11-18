package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2748
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        if(n == 0)
        {
            System.out.print(0);
            return;
        }
        long[] dp = new long[n + 1]; // long 타입으로 선언해서 오브플로우 방지
        dp[0] = 0;
        if(n >= 1)
        {
            dp[1] = 1;
        }
        for(int i=2; i<n+1; i++)
        {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.print(dp[n]);

    }
}
