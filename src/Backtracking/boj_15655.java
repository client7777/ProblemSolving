package Backtracking;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15655
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
            arr2 = new int[m];// m개의 숫자를 골라서 수열을 만들 배열
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++)
            {
                    //n개의 숫자를 입력받아 배열에 저장
                    arr1[i] = Integer.parseInt(st.nextToken());
            }
        Arrays.sort(arr1); // 배열을 오름차순으로 정렬
        dfs(0,0);
        System.out.print(sb.toString());
    }
    public static void dfs(int at, int depth)
    {
        // m개의 숫자를 모두 골라서 수열을 만들었다면
        if(depth == m)
        {
            for(int val:arr2)
            {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=at; i<n; i++)
        {
            arr2[depth] = arr1[i];
            dfs(i+1, depth+1);
        }
    }
}
