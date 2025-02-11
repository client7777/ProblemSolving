package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2231
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int ans = 0;

        for(int i=0; i<n; i++)
        {
            int num = i;
            int sum = 0;

            while (num != 0)
            {
                sum += num % 10;
                num /= 10;
            }

            if(sum + i == n)
            {
                ans = i;
                break;
            }
        }
        System.out.print(ans);
    }
}
