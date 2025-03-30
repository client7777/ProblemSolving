package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2908
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str1 = st.nextToken();
        String str2 = st.nextToken();

        StringBuilder sb = new StringBuilder(str1);
        int reverseNum1 = Integer.parseInt(String.valueOf(sb.reverse()));

        StringBuilder sb1 = new StringBuilder(str2);
        int reverseNUm2 = Integer.parseInt(String.valueOf(sb1.reverse()));

        System.out.print(Math.max(reverseNum1, reverseNUm2));

    }
}
