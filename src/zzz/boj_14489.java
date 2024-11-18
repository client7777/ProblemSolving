package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_14489
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int chick = Integer.parseInt(br.readLine())*2;

        if(a+b>=chick)
        {
            System.out.print((a+b)-chick);
        }
        else
            System.out.print(a+b);
    }
}
