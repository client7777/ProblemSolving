package Backtracking;

import java.io.*;
import java.util.*;

public class boj_16987
{
    static int n;
    static int[] dura, weight;
    static int ans = 0;
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dura = new int[n];
        weight = new int[n];
        StringTokenizer st;

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            dura[i] = Integer.parseInt(st.nextToken()); // 내구도
            weight[i] = Integer.parseInt(st.nextToken()); // 무게
        }

        dfs(0);
        System.out.println(ans);
    }
    static void dfs(int depth)
    {
        // 모든 계란에 대해 한 번씩 시도해 봤다면
        if(depth == n)
        {
            int cnt = 0;
            for(int i=0; i<n; i++)
            {
                if(dura[i] <= 0)
                {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        if(dura[depth] <= 0)
        {
            // 현재 계란이 이미 깨진 경우 다음 계란으로 넘어감
            dfs(depth+1);
            return;
        }
        boolean broken = false;
        for(int i=0; i<n; i++)
        {
            //현재 계란이 아니고 깨지지 않은 계란을 선택
            if(i != depth && dura[i] > 0)
            {
                dura[depth] -= weight[i];
                dura[i] -= weight[depth];

                broken = true;

                dfs(depth+1);

                dura[depth] += weight[i];
                dura[i] += weight[depth];
            }
        }
        //현재 들고있는 계란으로 칠 수 있는 계란이 존재하지 않을 경우 다음 계란으로 넘어가기 위한 문장
        if(!broken)
        {
            dfs(depth + 1);
        }
    }
}
