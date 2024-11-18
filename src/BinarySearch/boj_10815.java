package BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_10815
{
    static int n,m;
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
        m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            int target = Integer.parseInt(st.nextToken());
            if(binarySearch(target))
                sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.print(sb);
    }
    static boolean binarySearch(int target)
    {
        int st = 0;
        int en = n-1;
        while (st <= en)
        {
            int mid = (st + en)/2;
            if(arr[mid] > target)
            {
                en = mid-1;
            }
            else if(arr[mid] < target)
            {
                st = mid+1;
            }
            else
                return true;
        }
        return false;
    }
}
