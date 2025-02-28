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

        boolean[][] map = new boolean[n+1][n+1];
        for(int i=1; i<=n-1; i++)
        {
            int m = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                int num = Integer.parseInt(st.nextToken());

                map[i][num] = true;
            }
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(map[i][k] && map[k][j]) map[i][j] = true;
                }
            }
        }

        String ans = "NO CYCLE";
        for(int i=1; i<=n; i++)
        {
            if(map[1][i] && map[i][i])
            {
                ans = "CYCLE";
            }
        }

        System.out.print(ans);
    }
}
