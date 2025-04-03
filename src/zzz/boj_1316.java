package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1316
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        int cnt = 0;

        while (test_case -- > 0)
        {
            String str = br.readLine();

            if(check(str)) cnt++;
        }

        System.out.print(cnt);
    }

    static boolean check(String str)
    {
        int prev = 0;
        boolean[] isUsed = new boolean[26];

        char[] ch = str.toCharArray();

        for(char c : ch)
        {
            if(prev != c)
            {
                if(!isUsed[c - 'a'])
                {
                    isUsed[c - 'a'] = true;
                    prev = c;
                }
                else
                    return false;
            }
        }

        return true;
    }
}
