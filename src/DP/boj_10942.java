package DP;
// O(n^2 + m)
// 팰린드롬 -> 앞에서 읽으나 뒤로 읽으나 동일한 문자열이나 수
import java.io.*;
import java.util.StringTokenizer;

public class boj_10942
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
        int[][] d = new int[n+1][n+1]; // d[i][j] = i번째에서 j번째까지 수가 팰린드롬인지

        //길이가 1, 2인 팰린드롬 초기화
        for(int i=1; i<=n; i++)
        {
            d[i][i] = 1;
        }
        for(int i=1; i<n; i++)
        {
            if(arr[i] == arr[i+1])
            {
                d[i][i+1] = 1;
            }
        }

        for(int l = 3; l<=n; l++)
        {
            for(int i=1; i<= n - l + 1; i++)
            {
                int j = i + l - 1;
                if(arr[i] == arr[j] && d[i+1][j-1] == 1)
                    d[i][j] = 1;
            }
        }
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(d[s][e]).append('\n');
        }
        System.out.print(sb);
    }
}
