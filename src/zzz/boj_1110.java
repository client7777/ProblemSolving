package zzz;

import java.io.*;

public class boj_1110
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int next = n;

        int cnt = 0;
        while (true)
        {
            cnt ++;

            n = ((n % 10) * 10 + (n / 10 + n % 10) % 10);

            if(n == next) break;
        }
        System.out.print(cnt);
    }
}
