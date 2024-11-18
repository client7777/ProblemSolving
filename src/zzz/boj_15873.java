package zzz;

import java.io.*;
import java.util.*;

public class boj_15873
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if(str.charAt(1) == '0')
        {
            System.out.print(10 + Integer.parseInt(str.substring(2)));
        }
        else
        {
            System.out.print(str.charAt(0)-'0' + Integer.parseInt(str.substring(1)));
        }
    }
}
