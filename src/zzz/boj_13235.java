package zzz;

import java.io.*;

public class boj_13235
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();
        boolean flag = true;
        if(length == 1)
        {
            System.out.print(flag);
            return;
        }
        else
        {
            for(int i=0; i<length/2; i++)
            {
                if(str.charAt(i) != str.charAt(length-1-i))
                {
                    flag = false;
                    break;
                }
            }
        }
        System.out.print(flag);
    }
}
