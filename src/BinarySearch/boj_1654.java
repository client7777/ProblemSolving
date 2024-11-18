package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1654
{
    static int k,n;
    static int[] arr;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[k];
        int max = 0;
        for(int i=0; i<k; i++)
        {
           arr[i] = Integer.parseInt(br.readLine());
           max = Math.max(max, arr[i]);
        }
        long low = 1;
        long hi = max;
        while (low < hi)
        {
            long mid = (low + hi + 1)/2;
            if(solve(mid)) low = mid;
            else hi = mid-1;
        }
        System.out.print(low);
    }
    static boolean solve(long x)
    {
        long cur = 0;
        for(int i=0; i<k; i++) cur += arr[i]/x;
        return cur >= n;
    }
}
