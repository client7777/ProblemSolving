package zzz;

import java.io.*;
import java.util.*;

public class boj_9012
{
    static int test_case;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());
        for(int t=0; t<test_case; t++)
        {
            String str = br.readLine();
            solve(str);
        }
        System.out.print(sb);
    }
    static void solve(String str)
    {
        Stack<Character> stack = new Stack<>();

        int len = str.length();

        for(int i=0; i<len; i++)
        {
            char ch = str.charAt(i);

            if(ch == '(')
            {
                stack.add(ch);
            }
            else if(stack.isEmpty())
            {
                sb.append("NO").append('\n');
                return;
            }
            else
                stack.pop();
        }
        if(stack.isEmpty())
        {
            sb.append("YES").append('\n');
        }
        else
            sb.append("NO").append('\n');
    }
}
