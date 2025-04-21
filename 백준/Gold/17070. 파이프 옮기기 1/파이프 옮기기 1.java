import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static int answer = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,1,1);
        System.out.print(answer);
    }

    static void dfs(int x,int y,int dir)
    {
        if(x == n-1 && y == n-1)
        {
            answer++;
            return;
        }

        if(dir == 1 || dir == 3)
        {
            if(y + 1 < n && map[x][y+1] == 0)
            {
                dfs(x,y+1,1);
            }
        }

        if(dir == 2 || dir == 3)
        {
            if(x + 1 < n && map[x+1][y] == 0)
            {
                dfs(x+1, y, 2);
            }
        }

        if(x + 1 < n && y + 1 < n)
        {
            if(map[x+1][y+1] == 0 && map[x+1][y] == 0 && map[x][y+1] == 0)
            {
                dfs(x+1, y+1, 3);
            }
        }
    }
}
