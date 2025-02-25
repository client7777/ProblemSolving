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

        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());

        for(int i=0; i<q; i++)
        {
            st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());

            if(query == 1)
            {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                int cnt = 0;
                for(int j=l; j<=r; j++)
                {
                    if(arr[j] == k) cnt++;
                }

                sb.append(cnt).append('\n');
            }

            else if(query == 2)
            {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                for(int j=l; j<=r; j++)
                {
                    arr[j] = -1;
                }
            }
        }

        System.out.print(sb);

    }
}
