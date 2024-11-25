package Backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15663
{
    static int n,m;
    static int[] arr1, arr2;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr1 = new int[n];
        arr2 = new int[m];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);
        backTrack(0);
        System.out.print(sb);
    }
    static void backTrack(int depth)
    {
        if(depth == m)
        {
            for(int val:arr2)
                sb.append(val + " ");
            sb.append('\n');
            return;
        }
        int tmp = -1; // 현재 깊이에서 같은 숫자가 선택되지 않도록 하기 위한 변수
        for(int i=0; i<n; i++)
        {
            if(!visit[i] && tmp != arr1[i])
            {
                arr2[depth] = arr1[i];
                visit[i] = true;
                tmp = arr2[depth];
                backTrack(depth + 1);
                visit[i] = false;
            }
        }
    }
}
