package BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_10816
{
    static int n;
    static int[] arr;
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
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            int target = Integer.parseInt(st.nextToken());
            sb.append(upper(target) - lower(target)).append(' ');
        }
        System.out.print(sb);
    }
    static int lower(int n)
    {
        int low = 0;
        int hi = arr.length;
        while (low < hi)
        {
            int mid = (low + hi)/2;
            if(arr[mid] >= n)
            {
                hi = mid;
            }
            else
                low = mid + 1;
        }
        return low;
    }
    static int upper(int n)
    {
        int low = 0;
        int hi = arr.length;
        while (low < hi)
        {
            int mid = (hi + low)/2;
            if(arr[mid] > n) hi = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}
