package DP;

import java.io.*;
import java.util.*;

public class boj_2156
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(n == 1)
        {
            System.out.print(arr[1]);
            return;
        }
        //dp[i] = i번째 잔까지 도달했을 때 마신 최대 포도주의 양
        int[] dp = new int[n+1];
        dp[1] = arr[1]; // 첫번째 포도잔을 마심
        dp[2] = arr[1] + arr[2]; // 첫번째, 두번째 포도잔을 연속으로 마심

        for(int i=3; i<=n; i++)
        {
            //1.이번 잔을 먹지 않는 경우
            //2.이번 잔을 먹고 직전 잔을 건너 뛰는 경우.
            //3.이번 잔과 직전 잔을 마시는 경우
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i] + arr[i-1]));
        }
        System.out.print(dp[n]);
    }
}
//최소 1잔 이상의 포도주를 보장하므로 dp[0] 값은 참조할 일이 없다.