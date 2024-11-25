package Backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15656
{
    static int[] arr1;
    static int[] arr2;
    static int n,m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr1 = new int[n]; // n개의 숫자를 입력받아 저장할 배열
            arr2 = new int[m]; // m개의 숫자를 골라 완성시킨 수열을 저장할 배열

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++)
            {
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr1);
            dfs(0);
            System.out.print(sb.toString());
    }
    static void dfs(int depth)
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
            for(int i=0; i<n; i++)
            {
                arr2[depth] = arr1[i];
                dfs(depth+1);
            }
    }
}
