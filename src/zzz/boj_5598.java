package zzz;

import java.io.*;
import java.util.ArrayList;

public class boj_5598
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        char[] ch = str.toCharArray();;
        ArrayList<Character> ans = new ArrayList<>();

        for(char c:ch)
        {
            int tmp = 0;
            if(c >= 'D')
            {
                tmp = c - 3;
                ans.add((char)tmp);
            }
            else
            {
                tmp = c + 23;
                ans.add((char)tmp);
            }
        }
        for(char a:ans)
        {
            System.out.print(a);
        }
    }
}
