package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_25628
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken())/2;
        int b = Integer.parseInt(st.nextToken());

        System.out.print(Math.min(a,b));
    }
}
