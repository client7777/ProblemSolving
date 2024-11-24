package zzz;

import java.io.*;

public class boj_2057
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        if(n == 0) // 팩토리얼은 항상 양의 정수를 곱한 값이기 때문에 어떤 팩토리얼 값도 0이 될 수 없다.
        {
            System.out.print("NO");
            return;
        }

        long[] factoryNum = new long[21]; // 21! 이상은 범위를 초과하므로 고려 x
        factoryNum[0] = 1;

        for(int i=1; i<=20; i++)
        {
            factoryNum[i] = factoryNum[i-1] * i;
        }

        //큰 수부터 차례대로 선택, 그리디
        for(int i=20; i>=0; i--)
        {
            if(n >= factoryNum[i]) n -= factoryNum[i];
        }
        System.out.print(n == 0 ? "YES" : "NO");
    }
}
