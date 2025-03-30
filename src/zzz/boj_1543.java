package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1543
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String search = br.readLine();

        str = str.replaceAll(search, "1");

        int answer = 0;

        for(int i=0; i<str.length(); i++)
        {
            if(str.charAt(i) == '1') answer++;
        }

        System.out.print(answer);
    }
}
