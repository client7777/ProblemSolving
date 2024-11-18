package DP;

import java.io.*;
import java.util.StringTokenizer;

public class boj_4883
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = 1;
        while (true)
        {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            int[][] arr = new int[n][3]; // 입력을 받을 배열

            StringTokenizer st;
            for(int i=0; i<n; i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<3; j++)
                {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] d = new int[n][3]; // i행 j열까지 최소 비용
            d[0][0] = Integer.MAX_VALUE;
            d[0][1] = arr[0][1];
            d[0][2] = arr[0][1] + arr[0][2];
            for(int i=1; i<n; i++)
            {
               d[i][0] = Math.min(d[i-1][0], d[i-1][1]) + arr[i][0];
               d[i][1] = Math.min(Math.min(d[i][0], d[i-1][0]), Math.min(d[i-1][1], d[i-1][2])) + arr[i][1];
               d[i][2] = Math.min(d[i][1], Math.min(d[i-1][1], d[i-1][2])) + arr[i][2];
            }
            sb.append(test_case + ". " + d[n-1][1]).append('\n');
            test_case++;
        }
        System.out.print(sb);
    }
}
