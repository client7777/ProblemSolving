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
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        long cnt = 0;

        boolean[] check = new boolean[100001];
        while (true)
        {
            while (right < n && !check[arr[right]])
            {
                check[arr[right]] = true;
                right++;
            }

            cnt += (right - left);
            check[arr[left]] = false;
            left++;

            if(left == n) break;
        }

        System.out.print(cnt);
    }
}
