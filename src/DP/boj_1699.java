package DP;

import java.io.*;

public class boj_1699
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] d = new int[100001]; // d[i] = i의 제곱수 항의 최소 개수
        d[1] = 1;
        
        for(int i=2; i<=100000; i++)
        {
            d[i] = i; // 최악의 경우 i를 i개의 항으로 나타냄
            for(int j=1; j*j<=i; j++)
            {
                // i를 표현할 때 j*j라는 제곱수를 하나의 항으로 사용하고 나머지 부분을 d[i - (j*j)] 결과와 합쳐서 항의 개수를 구함
                d[i] = Math.min(d[i], d[i - (j*j)] + 1);
            }
        }
        System.out.print(d[n]);
    }
}
