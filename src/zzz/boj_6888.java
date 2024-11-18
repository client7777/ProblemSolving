package zzz;

import java.io.*;

public class boj_6888
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        for(int i=a; i<=b; i += 60)
        {
            System.out.println("All positions change in year "+i);
        }
    }
}
