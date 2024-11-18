package zzz;

import java.io.*;
import java.util.*;

public class boj_21734
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        
        char[] ch = str.toCharArray(); // 입력받은 문자열을 문자형 배열에 하나씩 저장

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<ch.length; i++)
        {
            int ascii = ch[i];
            int cnt = 0;
            while (ascii > 0)
            {
                cnt += ascii % 10;
                ascii /= 10;
            }
            while (cnt > 0)
            {
                sb.append(ch[i]);
                cnt--;
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
