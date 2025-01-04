package zzz;

import java.io.*;

public class boj_20735
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();

            if(str.toLowerCase().contains("pink") || str.toLowerCase().contains("rose")) cnt++;
        }
        System.out.print(cnt == 0 ? "I must watch Star Wars with my daughter" : cnt);
    }
}
