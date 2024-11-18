package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11052
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] price = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            price[i] = Integer.parseInt(st.nextToken());
        }
        int[] d = new int[n+1]; // i개의 카드를 샀을 때 지불해야 하는 금액의 최댓값
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=i; j++)
            {
                d[i] = Math.max(d[i], d[i-j] + price[j]); // 카드가 j개 들어있는 카드팩을 구매하고 카드 i-1개를 구입
            }
        }
        System.out.print(d[n]);
    }
}