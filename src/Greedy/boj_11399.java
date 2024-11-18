package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_11399
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int sum = 0;
        int accumulate = 0;
        for(int i=0; i<n; i++)
        {
            accumulate += arr[i];
            sum += accumulate;
        }
        System.out.print(sum);
    }
}
/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int[] dp = new int[n];
        dp[0] = arr[0]; // 첫번째 사람
        //n이 1이하인 경우 1인덱스에 접근이 불가능하다. 따라서 n > 1 인 경우에만 해당 값이 정의되도록 함
        if(n > 1)
        {
            dp[1] = dp[0] + arr[1]; // 두번째 사람
        }
        for(int i=2; i<n; i++)
        {
            dp[i] = dp[i-1] + arr[i];
        }
        int sum = 0;
        for(int val:dp)
        {
            sum += val;
        }
        System.out.print(sum);
    }
}

 */