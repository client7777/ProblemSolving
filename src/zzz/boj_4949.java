package zzz;

import java.io.*;
import java.util.*;

public class boj_4949
{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;

        while (true)
        {
            str = br.readLine();
            if(str.equals(".")) break;
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

            if(ch == '(' || ch == '[')
            {
                stack.add(ch);
            }
            else if(ch == ')')
            {
                if(stack.isEmpty() || stack.pop() != '(')
                {
                    sb.append("no").append("\n");
                    return;
                }
            }
            else if(ch == ']')
            {
                if(stack.isEmpty() || stack.pop() != '[')
                {
                    sb.append("no").append("\n");
                    return;
                }
            }
        }
        if(stack.isEmpty())
        {
            sb.append("yes").append('\n');
        }
        else
            sb.append("no").append('\n');
    }
}
