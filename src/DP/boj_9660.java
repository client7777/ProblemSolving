package DP;

import java.io.*;

public class boj_9660
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.print((n % 7 == 0 || n % 7 == 2) ? "CY" : "SK");
    }
}
