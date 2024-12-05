package zzz;

import java.io.*;
import java.math.BigInteger;

public class boj_27434
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 0)
        {
            System.out.print(1);
            return;
        }
        System.out.print(facto(1, n));
    }
    static BigInteger facto(int start, int end)
    {
        if(start == end) return BigInteger.valueOf(end);

        return facto(start, (start + end)/2).multiply(facto((start + end)/2 + 1, end));
    }
}
