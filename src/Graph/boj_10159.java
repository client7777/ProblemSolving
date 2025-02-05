package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_10159
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] check = new boolean[n+1][n+1];

        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            check[a][b] = true;
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(check[i][k] && check[k][j])
                        check[i][j] = true;
                }
            }
        }

        int[] ans = new int[n+1];

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(check[i][j] || check[j][i]) ans[i]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(n - 1 - (ans[i])).append('\n');
        }

        System.out.print(sb);
    }
}
