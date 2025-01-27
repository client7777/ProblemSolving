package zzz;

import java.io.*;

public class boj_1864
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true)
        {
            String str = br.readLine();

            if(str.equals("#")) break;

            int length = str.length();

            int num = 0;

            for(int i=0; i<length; i++)
            {
                if(str.charAt(i) == '-')
                {
                    num += 0 * Math.pow(8, (length -1) -i);
                }
                else  if(str.charAt(i) == '\\')
                {
                    num += 1 * Math.pow(8, (length -1)-i);
                }
                else  if(str.charAt(i) == '(')
                {
                    num += 2 * Math.pow(8, (length -1)-i);
                }
                else  if(str.charAt(i) == '@')
                {
                    num += 3 * Math.pow(8, (length -1)-i);
                }
                else  if(str.charAt(i) == '?')
                {
                    num += 4 * Math.pow(8, (length -1)-i);
                }
                else  if(str.charAt(i) == '>')
                {
                    num += 5 * Math.pow(8, (length -1)-i);
                }
                else  if(str.charAt(i) == '&')
                {
                    num += 6 * Math.pow(8, (length -1)-i);
                }
                else  if(str.charAt(i) == '%')
                {
                    num += 7 * Math.pow(8, (length -1)-i);
                }
                else
                {
                    num += -1 * Math.pow(8, (length -1)-i);
                }

            }
            sb.append(num).append('\n');
        }
        System.out.print(sb);
    }
}
