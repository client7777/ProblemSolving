package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2985
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if(a + b == c)
        {
            System.out.println(a + "+" + b + "=" + c);
        }
        else if(a - b == c)
        {
            System.out.println(a + "-" + b + "=" + c);
        }
        else if(a * b == c)
        {
            System.out.println(a + "*" + b + "=" + c);
        }
        else if(a / b == c)
        {
            System.out.println(a + "/" + b + "=" + c);
        }
        else if(a == b + c)
        {
            System.out.println(a + "=" + b + "+" + c);
        }
        else if(a == b - c)
        {
            System.out.println(a + "=" + b + "-" + c);
        }
        else if(a == b * c)
        {
            System.out.println(a + "=" + b + "*" + c);
        }
        else if(a == b / c)
        {
            System.out.println(a + "=" + b + "/" + c);
        }
    }
}
