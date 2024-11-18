package zzz;

import java.io.*;

public class boj_27433
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.print(factorial(n));
    }
    static long factorial(int n)
    {
        if(n<=0)
            return 1;
        return n * factorial(n-1);
    }
}
