package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_25629
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int odd = 0;
        int even = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            if(num % 2 ==0)
            {
                even++;
            }
            else
                odd++;
        }

        if(odd == even || odd - even == 1)
        {
            System.out.print(1);
        }
        else
            System.out.print(0);
    }
}
/*
        if(n%2==0) //n이 짝수인 경우 짝수와 홀수의 개수가 같으면 가능
        {
            System.out.print(odd == even ? 1 : 0);
        }
        else //n이 홀수인 경우 홀수의 개수가 짝수보다 1개 많으면 가능
            System.out.print(odd - even == 1 ? 1 : 0);
*/
