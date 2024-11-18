package DP;

import java.io.*;
import java.util.StringTokenizer;

public class boj_9465
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = Integer.parseInt(br.readLine());
        for(int t=0; t<test_case; t++)
        {
            int n = Integer.parseInt(br.readLine());
            int[][] note = new int[2][n+1];
            for(int i=0; i<2; i++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=1; j<=n; j++)
                {
                    note[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] d = new int[2][n+1];
            d[0][1] = note[0][1];
            d[1][1] = note[1][1];
            for(int j=2; j<=n; j++)
            {
                d[0][j] = Math.max(d[1][j-1], d[1][j-2]) + note[0][j];
                d[1][j] = Math.max(d[0][j-1], d[0][j-2]) + note[1][j];
            }
            sb.append(Math.max(d[0][n],d[1][n])).append('\n');
        }
        System.out.print(sb);
    }
}
