package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_30501
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();

            if(str.contains("S"))
            {
                System.out.print(str);
                break;
            }
        }
    }
}
