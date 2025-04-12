package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_24417
{
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long cnt1 = fib(n) % MOD;
        long cnt2 = (n-2) %MOD;

        System.out.print(cnt1 + " " + cnt2);
    }

    static long fib(int n)
    {
        if(n == 1 || n == 2) return 1;

        long a = 1, b = 1, c = 0;
        for(int i=3; i<=n; i++)
        {
            c = (a + b) % MOD;
            a = b;
            b = c;
        }

        return c;
    }
}
