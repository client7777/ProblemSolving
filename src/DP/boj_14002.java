package DP;

import java.io.*;
import java.util.*;

public class boj_14002
{
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        //dp[i]: 수열의 첫 번째 원소부터 i번째 원소까지의 최대 증가 부분 수열의 길이
        int[] dp = new int[n + 1];
        int[] prev = new int[n + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            prev[i] = -1; //prev[i] = k -> i번째 원소는 k번째 원소로부터 이어짐
        }

        int maxLength = 0;
        int lastIdx = 0;

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j < i; j++)
            {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1)
                {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                }
            }
            if (dp[i] > maxLength)
            {
                maxLength = dp[i];
                lastIdx = i; // 최대 증가 부분 수열의 마지막 인덱스
            }
        }

        sb.append(maxLength).append('\n');

        Stack<Integer> stack = new Stack<>();
        while (lastIdx != -1)
        {
            stack.push(arr[lastIdx]);
            lastIdx = prev[lastIdx];
        }
        while (!stack.isEmpty())
        {
            sb.append(stack.pop()).append(" ");
        }
        System.out.print(sb.toString());
    }
}
