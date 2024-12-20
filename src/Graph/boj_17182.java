package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_17182
{
    static int n;
    static boolean[] visit;
    static int[][] map;
    static int MAX = Integer.MAX_VALUE/2;
    static int min;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                map[i][j] = (i == j) ? 0 : MAX;
            }
        }

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                int cost = Integer.parseInt(st.nextToken());
                map[i][j] = Math.min(map[i][j], cost);
            }
        }

        for(int l=0; l<n; l++)
        {
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if(map[i][l] != MAX && map[l][j] != MAX)
                    {
                        map[i][j] = Math.min(map[i][j], map[i][l] + map[l][j]);
                    }
                }
            }
        }

        min = MAX;
        visit = new boolean[n];
        visit[k] = true;
        backTrack(1, k, 0);
        System.out.print(min);
    }
    static void backTrack(int depth, int cur, int sum)
    {
        if(depth == n)
        {
            min = Math.min(min, sum);
            return;
        }
        for(int i=0; i<n; i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                backTrack(depth + 1, i, sum + map[cur][i]);
                visit[i] = false;
            }
        }
    }
}
