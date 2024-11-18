package DP;

import java.io.*;
import java.util.Stack;

public class boj_9252
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] d = new int[1001][1001];

        String str1 = br.readLine();
        String str2 = br.readLine();

        char[] ch1 = new char[str1.length()+1];
        char[] ch2 = new char[str2.length()+1];

        for(int i=1; i<=str1.length(); i++)
        {
            ch1[i] = str1.charAt(i-1);
        }
        for(int i=1; i<=str2.length(); i++)
        {
            ch2[i] = str2.charAt(i-1);
        }

        for(int i=1; i<=str1.length(); i++)
        {
            for(int j=1; j<=str2.length(); j++)
            {
                if(ch1[i] == ch2[j])
                {
                    d[i][j] = d[i-1][j-1] + 1;
                }
                else
                    d[i][j] = Math.max(d[i-1][j], d[i][j-1]);
            }
        }
        int i = str1.length();
        int j = str2.length();

        Stack<Character> stack = new Stack<>();
        while (i > 0 && j > 0)
        {
            if(str1.charAt(i-1) == str2.charAt(j-1))
            {
                stack.push(str1.charAt(i-1));
                i--;
                j--;
            }
            // str1에서 이전 위치로 이동
            // 더 길이가 긴 쪽을 선택
            else if(d[i-1][j] >= d[i][j-1])
            {
                i--;
            }
            // str2에서 이전 위치로 이동
            else
                j--;
        }
        StringBuilder sb = new StringBuilder();
        if(d[str1.length()][str2.length()] != 0)
        {
            sb.append(d[str1.length()][str2.length()]).append('\n');
            while (!stack.isEmpty())
            {
                sb.append(stack.pop());
            }
        }
        else
            sb.append(0);
        System.out.print(sb);
    }
}
