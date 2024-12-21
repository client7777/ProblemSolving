package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_26532
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int ac = (a*b) / 4840;

        System.out.print((ac) / 5 + 1);

    }
}
