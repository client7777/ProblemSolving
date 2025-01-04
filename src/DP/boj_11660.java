package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11660
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 표의 크기
        int m = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

        int[][] d = new int[n+1][n+1]; //d[i][j] = i,j 까지 누적 합
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                d[i][j] = d[i-1][j] + d[i][j-1] - d[i-1][j-1] + Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(d[x2][y2] - d[x1-1][y2] - d[x2][y1-1] + d[x1-1][y1-1]).append('\n');
        }
        System.out.print(sb);
    }
}
