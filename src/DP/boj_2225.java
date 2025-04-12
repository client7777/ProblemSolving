package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2225
{
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] d = new int[n+1][k+1]; // d[i][j] = 정수 j개를 더해서 정수 i를 만드는 방법의 수

        for(int i=0; i<=n; i++)
        {
            d[i][1] = 1; // 정수 1개를 가지고 만드는 방법의 수는 어떤 정수가 오든 1가지
        }

        for(int i=1; i<=k; i++)
        {
            d[0][i] = 1;
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=2; j<=k; j++)
            {
                d[i][j] = (d[i-1][j] + d[i][j-1]) % MOD;
            }
        }

        System.out.print(d[n][k] % MOD);
    }
}
