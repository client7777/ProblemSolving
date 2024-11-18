package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//각 배열의 요소들에 대해서 큰값에 작은값을 곱하면 됨
public class boj_1026
{
    public static void main(String[] args) throws IOException
    {
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           int n = Integer.parseInt(br.readLine());
           int[] a = new int[n];
           int[] b = new int[n];
           StringTokenizer st = new StringTokenizer(br.readLine());
           StringTokenizer st1 = new StringTokenizer(br.readLine());
           for(int i = 0; i < n; i++)
           {
               a[i] = Integer.parseInt(st.nextToken());
               b[i] = Integer.parseInt(st1.nextToken());
           }
           Arrays.sort(a);
           Arrays.sort(b);
           int ans = 0;
           for(int i = 0; i < n; i++)
           {
               ans += a[i] * b[n-i-1];
           }
           System.out.println(ans);
    }
}
