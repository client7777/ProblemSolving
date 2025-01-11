package zzz;

import java.io.*;

public class boj_21867
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String str = br.readLine();
        char[] ch = str.toCharArray();

        StringBuilder sb = new StringBuilder();
        for(char val:ch)
        {
            if(val == 'J' || val == 'A' || val == 'V')
            {
                n--;
                continue;
            }
            sb.append(val);

        }
        System.out.print(n == 0 ? "nojava" : sb);
    }
}
