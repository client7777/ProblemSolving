package zzz;

import java.io.*;

public class boj_2442
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n-i; j++)
                sb.append(' ');
            for(int j=1; j<=2*i-1; j++)
                sb.append('*');
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
