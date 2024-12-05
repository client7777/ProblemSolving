package zzz;

import java.io.*;

public class boj_2386
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true)
        {
            String str = br.readLine();

            if(str.equals("#")) break;

            str = str.toLowerCase();

            char target = str.charAt(0);

            int cnt = 0;

            String change = str.substring(2).toLowerCase();

            char[] ch = change.toCharArray();

            for(char c:ch)
            {
                if(target == c)
                    cnt++;
            }
            sb.append(target + " " + cnt).append('\n');
        }
        System.out.print(sb);
    }
}
