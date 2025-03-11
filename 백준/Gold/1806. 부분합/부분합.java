import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long min = Long.MAX_VALUE;
        long tot = arr[0];
        int end = 0;
        for(int start =0; start<n; start++)
        {
            while (end < n && tot < s)
            {
                end++;
                if(end != n) tot += arr[end];
            }
            if(end == n) break;
            min = Math.min(min, end - start + 1);
            tot -= arr[start];
        }
        System.out.print(min == Long.MAX_VALUE ? 0 : min);
    }
}
