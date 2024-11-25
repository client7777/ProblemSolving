package Backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15657
{
    static StringBuilder sb = new StringBuilder();
    static int n,m;
    static int[] arr1, arr2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr1 = new int[n];
        arr2 = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);

        backTrack(0,0);
        System.out.print(sb);
    }
    static void backTrack(int at, int depth)
    {
        if(depth == m)
        {
            for(int val:arr2)
            {
                sb.append(val + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i=at; i<n; i++)
        {
            arr2[depth] = arr1[i];
            backTrack(i, depth+1);
        }
    }
}
