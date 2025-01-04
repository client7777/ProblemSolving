package DP;

import java.io.*;

public class boj_2133
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n % 2 == 1)
        {
            System.out.print(0);
            return;
        }

        int[] d = new int[n+1];

        d[0] = 1;

        d[2] = 3;

        for(int i=4; i<=n; i++)
        {
            d[i] = d[i-2] * d[2];
            for(int j=i-4; j>=0; j -= 2)
            {
                d[i] += d[j] * 2;
            }
        }
        System.out.print(d[n]);
    }
}
