package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_11403
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // k -> 거쳐갈 노드
        // i에서 k로 갈 수 있는가? k에서 j로 갈 수 있는가?
        // i에서 j로 k를 거쳐서 갈 수 있는가? -> 시간복잡도 = O(n^3)
        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(map[i][k] == 1 && map[k][j] == 1)
                    {
                        map[i][j] = 1;
                    }
                }
            }
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
