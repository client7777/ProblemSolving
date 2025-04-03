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

        int[][] contest = new int[n+1][n+1];

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //a가 b를 이김
            contest[a][b] = 1;
            contest[b][a] = -1;
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(contest[i][k] == 1 && contest[k][j] == 1)
                    {
                        contest[i][j] = 1;
                        contest[j][i] = -1;
                    }

                    if(contest[i][k] == -1 && contest[k][j] == -1)
                    {
                        contest[i][j] = -1;
                        contest[j][i] = 1;
                    }
                }
            }
        }

        int ans = 0;
        for(int i=1; i<=n; i++)
        {
            int cnt = 0;

            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;

                if(contest[i][j] == 0) cnt++;
            }

            if(cnt == 0) ans++;
        }

        System.out.print(ans);
    }
}
