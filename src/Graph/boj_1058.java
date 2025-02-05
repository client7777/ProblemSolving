package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1058
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j) == 'Y' ? 1 : Integer.MAX_VALUE / 2;
            }
        }

        for(int k=0; k<n; k++)
        {
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int max = 0;

        for(int i=0; i<n; i++)
        {
            int cnt = 0;

            for(int j=0; j<n; j++)
            {
                if(i == j) continue;
                if(map[i][j] <= 2) cnt++;
            }
            max = Math.max(max, cnt);
        }
        System.out.print(max);
    }
}
