import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        for(int i=0; i<k; i++)
        {
            sum += arr[i];
        }

        max = sum;
        
       for(int right = k; right < n; right++)
       {
           sum += arr[right];
           sum -= arr[right - k];
           
           max = Math.max(max, sum);
       }

        System.out.print(max);
    }
}
