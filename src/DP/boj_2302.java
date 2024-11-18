package DP;

import java.io.*;

public class boj_2302
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        int[] d = new int[n+1]; // 연속한 i자리 좌석에서 앉을 수 있는 경우의 수

        d[0] = 1;
        d[1] = 1;
        if(n >= 2)
        {
            d[2] = 2;
        }
        for(int i=3; i<=n; i++)
        {
            d[i] = d[i-1] + d[i-2];
        }
        int ans = 1;
        int lastSeat = 0;
        for(int i=0; i<m; i++)
        {
            int tmp = Integer.parseInt(br.readLine());
            ans *= d[tmp - lastSeat -1];
            lastSeat = tmp;
        }
        ans *= d[n-lastSeat];
        System.out.print(ans);
    }
}
