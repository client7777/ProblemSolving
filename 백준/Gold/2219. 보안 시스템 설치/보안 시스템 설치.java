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
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                map[i][j] = Integer.MAX_VALUE / 2;
                if(i == j) map[i][j] = 0;
            }
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = Math.min(map[a][b], c);
            map[b][a] = Math.min(map[b][a], c);
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int ans = 0;
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++)
        {
            int sum = 0;
            for(int j=1; j<=n; j++)
            {
                sum += map[i][j];
            }

            if(min > sum)
            {
                min = sum;
                ans = i;
            }
        }

        System.out.print(ans);
    }
}
