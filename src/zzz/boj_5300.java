package zzz;

import java.io.*;

public class boj_5300
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++)
        {
            sb.append(i).append(" ");
            if(i%6 == 0 || i == n)
                sb.append("Go!").append(" ");
        }
        System.out.print(sb);
    }
}
