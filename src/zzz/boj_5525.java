package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_5525
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //Pn
        int m = Integer.parseInt(br.readLine()); //문자열 s의 길이

        String s = br.readLine();

        int cnt = 0;
        int res = 0;

        for(int i=1; i<m-1; i++)
        {
            if(s.charAt(i-1) == 'I' && s.charAt(i) == 'O' && s.charAt(i+1) == 'I')
            {
                cnt++;

                if(cnt >= n) res++;

                i++;
            }

            else cnt = 0;
        }

        System.out.print(res);
    }
}
