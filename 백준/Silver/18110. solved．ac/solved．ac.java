import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int exceptionSize = (int)Math.round(n * 0.15);

        Arrays.sort(arr);

        int sum = 0;
        for(int i=exceptionSize; i<n-exceptionSize; i++)
        {
            sum += arr[i];
        }
        System.out.print(Math.round((float) sum / (n - 2 * exceptionSize)));
    }
}
