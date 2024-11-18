package zzz;

import java.io.*;

public class boj_31306
{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        notVowel(str);
        vowel(str);
        System.out.print(sb);
    }

    static void notVowel(String str)
    {
        int cnt = 0;
        for(int i=0; i<str.length(); i++)
        {
            if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' ||
                    str.charAt(i) == 'u')
            {
                cnt ++;
            }
        }
        sb.append(cnt + " ");
    }
    static void vowel(String str)
    {
        int cnt = 0;
        for(int i=0; i<str.length(); i++)
        {
            if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' ||
                    str.charAt(i) == 'u'|| str.charAt(i) == 'y')
            {
                cnt ++;
            }
        }
        sb.append(cnt);
    }
}
