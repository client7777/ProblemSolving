package DP;

import java.io.*;
import java.util.*;

public class boj_12101
{
    static int len, n,k;
    static int[] num = {1,2,3};
    static int[] cur = new int[11]; //탐색한 수식을 담을 배열
    public static void main(String[] args) throws IOException
    {   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        backTrack(0,0);
        if(k!=0) // k번째 수식이 존재하지 않을 경우
        {
            System.out.print(-1);
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<len; i++)
            {
                sb.append(cur[i]);
                sb.append("+");
            }
            sb.deleteCharAt(sb.length()-1); //마지막 '+' 제거
            System.out.print(sb.toString());
        }
    }
    static void backTrack(int sum, int depth)
    {
        if(sum == n)
        {
            k--;
            len = depth; // 부분 수열의 길이
            return;
        }
        for(int i=0; i<3; i++)
        {
            if(sum + num[i] > n || k == 0) // 합이 n을 넘거나 이미 k번째 수식을 찾았을 경우 탐색 종료
            {
                break;
            }
            cur[depth] = num[i];
            backTrack(sum + num[i], depth + 1);
        }
    }
}
