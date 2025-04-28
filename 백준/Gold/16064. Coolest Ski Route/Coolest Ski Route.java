import java.io.*;
import java.util.*;

public class Main
{
    static final int INF = -987654321;
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
                if(i == j) continue;
                map[i][j] = INF;
            }
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[s][t] = Math.max(map[s][t], c);
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    map[i][j] = Math.max(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int answer = 0;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                answer = Math.max(answer, map[i][j]);
            }
        }

        System.out.print(answer);
    }
}
