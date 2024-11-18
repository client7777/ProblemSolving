package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15654
{
    static int n,m;
    static int[] arr1;
    static int[] arr2;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr1 = new int[n]; // n개의 자연수를 저장할 배열
        arr2 = new int[m]; // 길이가 m인 수열을 만들 배열
        visit = new boolean[10001];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);
        dfs(0);
        System.out.println(sb);
    }
    static void dfs(int depth)
    {
        //m개의 숫자를 골랐다면 출력
        if(depth == m)
        {
            for(int val:arr2)
            {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=0; i<n; i++)
        {
            if(!visit[arr1[i]])
            {
                visit[arr1[i]] = true;
                arr2[depth] = arr1[i];
                dfs(depth+1);
                visit[arr1[i]] = false;
            }
        }
    }
}
