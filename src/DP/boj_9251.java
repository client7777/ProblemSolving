package DP;

import java.io.*;

public class boj_9251
{
    public static void main(String[] args) throws IOException
    {
        int[][] d = new int[1005][1005]; // d[i][j] = a의 i-1번째 글자와 b의 j-1번째 글자까지의 최장 공통 부분 수열의 길이

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        int len_a = a.length();
        int len_b = b.length();

        char[] ch_a = a.toCharArray();
        char[] ch_b = b.toCharArray();

        for(int i=1; i<=a.length(); i++)
        {
            for(int j=1; j<=b.length(); j++)
            {
                //현재 칸에 들어갈 값은 대각선 왼쪽 위의 값 + 1이다.
                if(ch_a[i-1] == ch_b[j-1])
                {
                    d[i][j] = d[i-1][j-1] + 1;
                }
                // 문자가 다를 경우 현재 위치를 기준으로 왼쪽 값과 위쪽 값 중 더 큰 값이 들어옴
                else d[i][j] = Math.max(d[i-1][j], d[i][j-1]); 
            }
        }
        System.out.print(d[len_a][len_b]);
    }
}
