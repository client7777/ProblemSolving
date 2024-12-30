package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1915
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n+1][m+1];
        for(int i=1; i<=n; i++)
        {
            String str = br.readLine();
            for(int j=1; j<=m; j++)
            {
                map[i][j] = str.charAt(j-1);
            }
        }

        int[][] d = new int[n+1][m+1];
        int ans = 0;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=m; j++)
            {
                if(map[i][j]== '0') continue;
                d[i][j] = Math.min(d[i-1][j], Math.min(d[i][j-1], d[i-1][j-1])) + 1;
                ans = Math.max(ans, d[i][j]);
            }
        }
        System.out.print(ans * ans);
    }
}
