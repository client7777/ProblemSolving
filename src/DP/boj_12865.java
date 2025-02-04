package DP;
//평범한 배낭, Knapsack
import java.io.*;
import java.util.StringTokenizer;

public class boj_12865
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] w = new int[n+1];
        int[] v = new int[n+1];
        int[] d = new int[k+1];

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++)
        {
            for(int j = k; j >= w[i]; j--)
            {
                d[j] = Math.max(d[j], d[j - w[i]] + v[i]);
            }
        }
        System.out.print(d[k]);
    }
}
/*

import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] w = new int[n+1];
        int[] v = new int[n+1];
        int[][] d = new int[n+1][k+1];

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=k; j++)
            {
                if(w[i] > j)
                {
                    d[i][j] = d[i-1][j];
                }
                else
                {
                    d[i][j] = Math.max(d[i-1][j], d[i-1][j - w[i]] + v[i]);
                }
            }
        }
        System.out.print(d[n][k]);
    }
}

* */
