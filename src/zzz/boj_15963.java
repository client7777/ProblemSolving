package zzz;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class boj_15963
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        System.out.print(n == m ? 1 : 0);
    }
}
