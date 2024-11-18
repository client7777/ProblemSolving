package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_15151
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long k = Long.parseLong(st.nextToken());
        long d = Long.parseLong(st.nextToken());

        long cnt = 0;
        long sum = 0;
        long idx = 1;

        while (sum + k * idx <= d)
        {
            sum += k * idx;
            cnt++;
            idx *= 2;
        }
        System.out.print(cnt);
    }
}
