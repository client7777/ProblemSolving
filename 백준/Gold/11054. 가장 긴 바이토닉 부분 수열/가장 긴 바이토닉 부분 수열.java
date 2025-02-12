import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] d_left = new int[n+1];
        int[] d_right = new int[n+1];
        int[] arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            d_left[i] = 1;
            d_right[i] = 1;
        }

        //왼 -> 오 방향으로 증가하는 부분 수열
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<i; j++)
            {
                if(arr[i] > arr[j]) d_left[i] = Math.max(d_left[i], d_left[j] + 1);
            }
        }

        //오 -> 왼 방향으로 증가하는 부분 수열
        for(int i=n; i>0; i--)
        {
            for(int j=n; j>i; j--)
            {
                if(arr[i] > arr[j]) d_right[i] = Math.max(d_right[i], d_right[j] + 1);
            }
        }
        
        int max = 0;
        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, d_left[i] + d_right[i]);
        }

        System.out.print(max-1);
    }
}
