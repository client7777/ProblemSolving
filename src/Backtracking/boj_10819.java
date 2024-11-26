package Backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class boj_10819
{
    static int n;
    static int max = Integer.MIN_VALUE;
    static int[] arr, val;
    static boolean[] used;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        used = new boolean[n];
        val = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken()); // -100 <= n <= 100
        }
        backTrack(0);
        System.out.print(max);
    }
    static void backTrack(int depth)
    {
        if(depth == n)
        {
            cal();
            return;
        }
        for(int i=0; i<n; i++)
        {
            if(!used[i])
            {
                val[depth] = arr[i];
                used[i] = true;
                backTrack(depth+1);
                used[i] = false;
            }
        }
    }
    static void cal()
    {
        int value = 0;
        for(int i=1; i<n; i++)
        {
            value += Math.abs(val[i-1] - val[i]);
        }
        max = Math.max(max, value);
    }
}
