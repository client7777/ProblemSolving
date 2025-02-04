import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    static int[] d = new int[10001];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case-- > 0)
        {
            Arrays.fill(d, 0);
            
            int n = Integer.parseInt(br.readLine()); // 동전의 가지 수

            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++)
            {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine()); // 만들 금액

            d[0] = 1;
            for(int i=0; i<n; i++)
            {
                for(int j=arr[i]; j<=m; j++)
                {
                    d[j] += d[j - arr[i]];
                }
            }
            sb.append(d[m]).append('\n');
        }
        System.out.print(sb);
    }
}
