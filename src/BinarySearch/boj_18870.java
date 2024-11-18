package BinarySearch;
//좌표 압축
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_18870
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int[] remove_duplicate = Arrays.stream(sorted).distinct().toArray();

        StringBuilder sb = new StringBuilder();
        for(int num:arr)
        {
            sb.append(binarySearch(remove_duplicate, num) + " ");
        }
        System.out.print(sb);
    }
    static int binarySearch(int[] arr, int target)
    {
        int low = 0;
        int hi = arr.length-1;

        while (low <= hi)
        {
            int mid = (low + hi)/2;
            if(arr[mid] < target)
            {
                low = mid + 1;
            }
            else if(arr[mid] > target)
            {
                hi = mid -1;
            }
            else return mid;
        }
        return -1;
    }
}
