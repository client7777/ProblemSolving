package Backtracking;
// 중복을 허용한 수열
import java.io.*;
import java.util.StringTokenizer;

public class boj_15651
{
    static int n,m;
    static int[] arr;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m]; visit = new boolean[n+1];
        dfs(0);
        System.out.print(sb.toString());

    }
    static void dfs(int depth)
    {
        if(depth == m)
        {
            for(int val:arr)
            {
                sb.append(val).append(' ');
            }
            sb.append("\n");
            return;
        }
        for(int i=1; i<=n; i++)
        {
            arr[depth] = i;
            dfs(depth+1);
        }
    }
}
