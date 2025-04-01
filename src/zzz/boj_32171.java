package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_32171
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int c = Integer.MIN_VALUE;
        int d = Integer.MIN_VALUE;

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Math.min(a, Integer.parseInt(st.nextToken()));
            b = Math.min(b, Integer.parseInt(st.nextToken()));
            c = Math.max(c, Integer.parseInt(st.nextToken()));
            d = Math.max(d, Integer.parseInt(st.nextToken()));

            sb.append((d - b) * 2 + (c - a) * 2).append('\n');
        }

        System.out.print(sb);
    }
}
