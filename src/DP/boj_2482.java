package DP;

import java.io.*;

public class boj_2482
{
    static final int DIVIDE = 1_000_000_003;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] d = new int[n+1][n+1]; // n개의 색상 중 k개의 색을 선택하는 경우의 수
        for(int i=1; i<=n; i++) d[i][1] = i; // i개중 1개를 고르는 방법의 수 -> i개

        for(int i=4; i<=n; i++)
        {
            for(int j=2; j<=n; j++) // 1개를 고르는 경우의 수는 이미 처리했으므로 2부터 시작
            {
                if(j > i/2) break; // n개 중에서 n/2개 초과해서 선택 불가능

                //i번째 색을 선택하지 않는 경우 -> d[i-1][j]
                //i번째 색을 선택하는 경우 -> d[i-2][j-1]
                d[i][j] = (d[i-1][j] + d[i-2][j-1]) % DIVIDE;
            }
        }
        System.out.print(d[n][k]);
    }
}
