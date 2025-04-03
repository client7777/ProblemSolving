package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_11005
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Stack<Character> stack = new Stack<>();

        while (n > 0)
        {
            if(n % b < 10)
            {
                stack.add((char)(n%b + '0'));
            }
            else stack.add((char)(n%b - 10 + 'A'));

            n /= b;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
        {
            sb.append(stack.pop());
        }

        System.out.print(sb);
    }
}
