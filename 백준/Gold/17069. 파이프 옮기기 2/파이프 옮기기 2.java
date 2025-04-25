import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    static int n;
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

        long[][][] d = new long[n][n][3]; // 0 -> 가로 1 -> 세로 2 -> 대각

        d[0][1][0] = 1; // 초기에는 가로로 시작

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 1) continue;
                
                //가로
                if(j + 1 < n && map[i][j+1] == 0)
                {
                    d[i][j+1][0] += (d[i][j][0] + d[i][j][2]);
                }

                //세로
                if(i + 1 < n && map[i+1][j] == 0)
                {
                    d[i+1][j][1] += (d[i][j][1] + d[i][j][2]);
                }

                //대각
                if(i + 1 < n && j + 1 < n)
                {
                    if(map[i+1][j+1] == 0 && map[i][j+1] == 0 && map[i+1][j] == 0)
                    {
                        d[i+1][j+1][2] += (d[i][j][0] + d[i][j][1] + d[i][j][2]);
                    }
                }
            }
        }
        
        System.out.print(d[n-1][n-1][0] + d[n-1][n-1][1] + d[n-1][n-1][2]);
    }
}
