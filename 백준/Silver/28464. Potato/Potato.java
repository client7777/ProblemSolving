import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int amount = 0;

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());

            amount += arr[i];
        }

        Arrays.sort(arr);

        int woo = 0;
        for(int i=0; i<n/2; i++)
        {
            woo += arr[i];
        }

        System.out.print(woo + " " + (amount - woo));

    }
}
