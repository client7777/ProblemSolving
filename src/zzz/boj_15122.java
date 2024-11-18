package zzz;

import java.io.*;

public class boj_15122
{

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine())+1;

        while (true)
        {
            String str = Integer.toString(n);
            if(str.contains("0"))
            {
                n++;
            }
            else
                break;
        }
        System.out.print(n);
    }
}
