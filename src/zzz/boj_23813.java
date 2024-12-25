package zzz;

import java.io.*;

public class boj_23813
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();

        int multi = 1;
        for(int i=0; i<n.length()-1; i++)
        {
            multi *= 10;
        }

        int num = Integer.parseInt(n);

        long sum = 0;
        int next = num;

        while (true)
        {
            sum += next;
            next = next/10 + next%10 * multi;
            if(next == num) break;
        }
        System.out.print(sum);
    }
}
