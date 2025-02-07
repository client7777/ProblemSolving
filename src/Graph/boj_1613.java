package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1613
{
    static final int INF = Integer.MAX_VALUE / 2;
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
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
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

        StringBuilder sb = new StringBuilder();

        int s = Integer.parseInt(br.readLine());

        for(int i=0; i<s; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(map[a][b] == INF && map[b][a] == INF) sb.append(0).append('\n');
            else if(map[a][b] != INF) sb.append(-1).append('\n');
            else if(map[b][a] != INF) sb.append(1).append('\n');
        }

        System.out.print(sb);
    }
}
