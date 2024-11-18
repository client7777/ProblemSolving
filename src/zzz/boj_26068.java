package zzz;

import java.io.*;

public class boj_26068
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i=0; i<n; i++)
        {
            if(Integer.parseInt(br.readLine().replace("D-",""))<=90) cnt++;
        }
        System.out.print(cnt);
    }
}
