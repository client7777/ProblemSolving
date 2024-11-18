package zzz;

import java.util.*;
import java.io.*;

public class boj_1681
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String notInclude = st.nextToken();

        int cnt = 0;
        while (n > 0)
        {
            cnt++;
            if(!String.valueOf(cnt).contains(notInclude))
            {
                n--;
            }
        }
        System.out.print(cnt);
    }
}
