package Backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class boj_15652
{
    static int n,m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        backTrack(1,0);
        System.out.print(sb.toString());
    }
    static void backTrack(int at, int depth)
    {
        if(depth == m)
        {
            for(int val:arr)
            {
                sb.append(val + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i=at; i<=n; i++)
        {
            arr[depth] = i;
            backTrack(i, depth+1);
        }
    }
}
