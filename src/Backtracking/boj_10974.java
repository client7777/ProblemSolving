package Backtracking;

import java.io.*;
import java.util.ArrayList;

public class boj_10974
{
    static int n;
    static boolean[] used;
    static ArrayList<Integer> arr = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        used = new boolean[n+1];

        backTrack(0);
        System.out.print(sb);
    }
    static void backTrack(int depth)
    {
        if(depth == n)
        {
            for(int val:arr)
            {
                sb.append(val + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i=1; i<=n; i++)
        {
            if(!used[i])
            {
                arr.add(i);
                used[i] = true;
                backTrack(depth+1);
                used[i] = false;
                arr.remove(arr.size()-1);
            }
        }
    }
}
