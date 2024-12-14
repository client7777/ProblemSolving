package zzz;

import java.io.*;

public class boj_31561
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 30분 이후에는 2/3배로 이동
        if(n > 30)
        {
            System.out.println((n * 3.0) / 2.0 - 30.0);
        }
        // 30분 이전에는 2배 빠르게 이동
        else
        {
            System.out.print(n/2.0);
        }
    }
}
