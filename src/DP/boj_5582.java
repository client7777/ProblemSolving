package DP;

import java.io.*;

public class boj_5582
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int[][] d = new int[4001][4001];

        char[] ch_a = a.toCharArray();
        char[] ch_b = b.toCharArray();

        int max = 0;
        for(int i=1; i<=a.length(); i++)
        {
            for(int j=1; j<=b.length(); j++)
            {
                if(ch_a[i-1] == ch_b[j-1])
                {
                    d[i][j] = d[i-1][j-1] + 1;

                }
                max = Math.max(max, d[i][j]);
            }
        }
        System.out.print(max);
    }
}
