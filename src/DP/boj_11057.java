package DP;

import java.io.*;

public class boj_11057
{
    static final int MOD = 10_007;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[][] d = new int[n+1][10]; // d[i][j] = 길이가 i인 j로 시작하는 오르막수의 개수

        //길이가 1인 오르막수는 자기 자신인 경우 1개 밖에 없음
        for(int i=0; i<10; i++)
        {
            d[1][i] = 1;
        }
        for(int i=2; i<=n; i++)
        {
            for(int j=0; j<10; j++)
            {
                for(int k=j; k<10; k++)
                {
                    d[i][j] = (d[i][j] + d[i-1][k]) % MOD;
                }
            }
        }
        int ans = 0;
        for(int val:d[n])
        {
            ans += val;
            ans %= MOD;
        }
        System.out.print(ans);
    }
}
