package zzz;

import java.io.*;

public class boj_10996
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=2*n; i++)
        {
            //홀수행
            if(i%2==1)
            {
                for(int j=1; j<=n; j++)
                {
                    //홀수번째 자리에는 별이 출력
                    if(j%2==1)
                    {
                        System.out.print("*");
                    }
                    else System.out.print(" ");
                }
            }
            //짝수행
            else
                for(int j=1; j<=n; j++)
                {
                    //홀수번째 자리에는 공백이 출력
                    if(j%2==1)
                    {
                        System.out.print(" ");
                    }
                    else
                        System.out.print("*");
                }
            System.out.println();

        }
    }
}
