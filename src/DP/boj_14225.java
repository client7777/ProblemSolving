package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14225
{
    static int n;
    static int[] arr;
    static boolean[] isUsed;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        isUsed = new boolean[100_000 * 20 + 1];

        dfs(0,0);

        for(int i=1; i<isUsed.length; i++)
        {
            if(!isUsed[i])
            {
                System.out.print(i);
                break;
            }
        }
    }

    static void dfs(int depth, int sum)
    {
        if(depth == n)
        {
            isUsed[sum] = true;
            return;
        }

        dfs(depth + 1, sum + arr[depth]);
        dfs(depth + 1, sum);
    }
}
