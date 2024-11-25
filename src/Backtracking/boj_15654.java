package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15654
{
    static int n,m;
    static int[] arr,ans;
    static boolean[] is_used;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        ans = new int[m];
        is_used = new boolean[100001];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        backTrack(0);
        System.out.print(sb);
    }
    static void backTrack(int depth)
    {
        if(depth == m)
        {
            for(int val:ans)
            {
                sb.append(val + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i=0; i<n; i++)
        {
            if(!is_used[arr[i]])
            {
                ans[depth] = arr[i];
                is_used[arr[i]] = true;
                backTrack(depth + 1);
                is_used[arr[i]] = false;
            }
        }
    }
}
