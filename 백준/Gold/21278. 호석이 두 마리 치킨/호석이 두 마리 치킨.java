import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;
                map[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = 1;

        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int num1 = 0;
        int num2 = 0;
        int minDist = Integer.MAX_VALUE / 2;

        for(int i=1; i<=n; i++)
        {
            for(int j=i+1; j<=n; j++)
            {
                int dist = 0;
                for(int k=1; k<=n; k++)
                {
                    if(i == k || j == k) continue;
                    dist += Math.min(map[i][k] , map[j][k]);
                }
                if(2 * dist < minDist)
                {
                    num1 = i;
                    num2 = j;
                    minDist = 2 * dist;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(num1).append(" ").append(num2).append(" ").append(minDist);
        System.out.print(sb);
    }
}
