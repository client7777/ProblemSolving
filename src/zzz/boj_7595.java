package zzz;

import java.io.*;

public class boj_7595
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            String str = br.readLine();
            if(str.equals("0")) break;
            int n = Integer.parseInt(str);
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=i; j++)
                {
                    sb.append("*");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
