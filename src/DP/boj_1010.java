package DP;
// 조합론
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1010
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        int[][] d = new int[31][31];

        for(int i=1; i<=30; i++)
        {
            d[i][1] = i; // n개 중에 1개를 뽑는 경우의 수
            d[i][0] = 1;
        }

        for(int i=2; i<=30; i++)
        {
            for(int j=1; j<=30; j++)
            {
                d[i][j] = d[i-1][j-1] + d[i-1][j];
            }
        }

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // M개중 N개를 뽑는 경우이므로 nCr 에서 n = M, r = N이다.
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(d[m][n]).append('\n');

        }
        System.out.print(sb);
    }
}
