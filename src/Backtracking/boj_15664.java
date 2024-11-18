package Backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15664
{
    static int n,m;
    static int[] arr1;
    static int[] arr2;
    static boolean visit[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr1 = new int[n];
        arr2 = new int[m];
        visit = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);
        dfs(0,0);
        System.out.print(sb.toString());
    }
    static void dfs(int at, int depth)
    {
        if(depth == m)
        {
            for(int val:arr2)
            {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        int tmp = -1;
        for(int i=at; i<n; i++)
        {
            if(!visit[i] && arr1[i] != tmp)
            {
                visit[i] = true;
                arr2[depth] = arr1[i];
                tmp = arr2[depth];
                dfs(i, depth+1);
                visit[i] = false;
            }
        }
    }
}
