package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15650
{
    static int n,m;
    static int[] arr;
    static boolean[] isUsed;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[m]; // 수열을 담을 배열
            isUsed = new boolean[n+1]; // 1부터 n까지 숫자의 사용 여부를 체크
            func(0);
            System.out.print(sb.toString());
    }

    static void func(int depth)
    {
        if(depth == m)
        {
            for(int val:arr)
            {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=1; i<=n; i++)
        {
            // 현재 선택할 숫자가 이전에 선택된 숫자보다 크거나 같아야 함.
            if(!isUsed[i] && (depth == 0 || i > arr[depth - 1]))
            {
                isUsed[i] = true;
                arr[depth] = i;
                func(depth+1);
                isUsed[i] = false;
            }
        }
    }
}
//depth == 0 은 첫 번째 위치에 숫자를 넣을 때 아무 제약 없이 선택할 수 있다.
//i > arr[depth - 1]는 현재 위치에 있는 숫자가 이전에 선택한 숫자보다 커야 함을 보장
/*
* public static void dfs(int at, int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = at; i <= N; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }*/
