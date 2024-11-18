package BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1920
{
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            int target = Integer.parseInt(st.nextToken());
            System.out.println(binarySearch(arr, target));
        }
    }
    static int binarySearch(int[] arr,int target)
    {
        int st = 0;
        int en = n-1;
        while (st <= en)
        {
            int mid = (st + en)/2;
            if(arr[mid] < target)
            {
                st = mid + 1;
            }
            else if(arr[mid] > target)
            {
                en = mid-1;
            }
            else
                return 1;
        }
        return 0;
    }
}
