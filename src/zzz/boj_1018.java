package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1018
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] board = new String[n];

        for(int i=0; i<n; i++) board[i] = br.readLine();

        int ans = Integer.MAX_VALUE;
        for(int i=0; i<=n-8; i++)
        {
            for(int j=0; j<=m-8; j++)
            {
                int res = solve(i,j,board);

                ans = Math.min(ans, res);
            }
        }

        System.out.print(ans);
    }

    static int solve(int row, int cal, String[] board)
    {
        String[] origin = {"WBWBWBWB", "BWBWBWBW"};
        int white = 0;

        for(int i=0; i<8; i++)
        {
            int x = row + i;
            for(int j=0; j<8; j++)
            {
                int y = cal + j;

                if(board[x].charAt(y) != origin[x % 2].charAt(j)) white++;
            }
        }

        return Math.min(white, 64 - white);
    }
}
