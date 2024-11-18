package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2240
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        // dp[i][j] = i초에 j번 이동하여 받을 수 있는 자두의 최대 개수
        int[][] dp = new int[t+1][w+1];
        // 매 초마다 자두가 떨어질 나무의 번호
        int[] treeNum = new int[t+1];
        for(int i=1; i<=t; i++)
        {
            st = new StringTokenizer(br.readLine());
            treeNum[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=t; i++)
        {
            for(int j=0; j<=w; j++)
            {
                // 현재 이동 횟수에 따른 나무의 위치를 결정, 초기에 0번 이동했으므로 초기 위치는 1번 나무에 위치
                int treePos = (j%2) + 1;
                dp[i][j] = dp[i-1][j] + (treePos == treeNum[i]?1:0);
                if(j > 0)
                {
                    // 이동하지 않은 상태에서의 최대 자두 개수와 이동한 후 자두를 받을 수 있는 상태
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + (treePos == treeNum[i]?1:0));
                }
            }
        }

        int ans = 0;
        // i=0부터 시작해야 함.
        for(int i=0; i<=w; i++)
        {
            ans = Math.max(ans, dp[t][i]);
        }
        System.out.print(ans);
    }
}
