package zzz;

import java.io.*;

public class boj_14043
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        System.out.print(check(str1, str2));

    }
    static String check(String str1, String str2)
    {
        int[] cnt = new int[26];

        for(char c:str1.toCharArray())
        {
            cnt[c - 'a'] ++;
        }

        for(char c:str2.toCharArray())
        {
            if(c != '*')
            {
                cnt[c - 'a']--;
                if(cnt[c - 'a'] < 0)
                    return "N";
            }
        }

        return "A";
    }
}
