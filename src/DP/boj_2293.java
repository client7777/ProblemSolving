package DP;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2293
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] val = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            val[i] = Integer.parseInt(br.readLine());
        }
        int[] d = new int[k+1]; // d[i] = i원을 만드는 경우의 수
        d[0] = 1; // 동전을 고르지 않고 0원을 만드는 경우의 수
        for(int i=1; i<=n; i++)
        {
            for(int j=val[i]; j<=k; j++)
            {
                d[j] += d[j-val[i]];
            }
        }
        System.out.print(d[k]);
    }
}
