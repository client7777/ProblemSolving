package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2294
{
    static final int MAX = 1000000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            coin[i] = Integer.parseInt(br.readLine());
        }
        //d[i] = i원을 만들기 위해 필요한 동전의 최소 개수
        int[] d = new int[k+1];
        Arrays.fill(d, MAX);
        d[0] = 0;
        for(int i=1; i<=n; i++)
        {
            for(int j=coin[i]; j<=k; j++)
            {
                d[j] = Math.min(d[j], d[j-coin[i]] + 1);
            }
        }
        System.out.print(d[k] == MAX ? -1 : d[k]);
    }
}
// 동전들의 단위가 목표 금액을 만들 수 없는 경우 -1 출력
