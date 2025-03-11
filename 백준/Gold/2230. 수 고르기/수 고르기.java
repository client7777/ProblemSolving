import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static long m;
    static long[] arr;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        arr = new long[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // 수열에서 두 수를 골랐을 때 (같은 수 가능) 차이가 m 이상면서 가장 작은 경우
        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        int end = 0;
        for(int start = 0 ; start < n; start++)
        {
            while (end < n && arr[end] - arr[start] < m) end++;
            if(end == n) break;
            min = Math.min(min, arr[end] - arr[start]);
        }
        System.out.print(min);
    }
}
