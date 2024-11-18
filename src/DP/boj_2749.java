package DP;
//피사노 주기 피보나치 수를 나눌 수를 k라고 할 때 k = 10^n 이면 피사노 주기는 15 * 10 ^ n-1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2749
{
    static final int DIVIDE = 1_000_000;
    static final int PISANO = 1_500_000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine()) % PISANO;

        long[] dp = new long[PISANO + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=PISANO; i++)
        {
            dp[i] = (dp[i-1] + dp[i-2]) % DIVIDE;
        }
        System.out.print(dp[(int)n]);
    }
}
