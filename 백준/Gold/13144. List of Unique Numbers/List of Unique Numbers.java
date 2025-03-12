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

        int[] arr = new int[n];
        boolean[] check = new boolean[100001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) 
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long cnt = 0;
        int right = 0;

        for (int start = 0; start < n; start++) 
        {
            while (right < n && !check[arr[right]]) 
            {
                check[arr[right]] = true;
                right++;
            }

            cnt += (right - start);
            check[arr[start]] = false;
        }

        System.out.print(cnt);
    }
}
