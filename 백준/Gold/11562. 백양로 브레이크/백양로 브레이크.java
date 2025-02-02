import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int INF = Integer.MAX_VALUE /2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            Arrays.fill(map[i], INF);
            map[i][i] = 0; // 자기 자신으로 향하는 비용은 0
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(b == 0) // u -> v로 가는 일방통행
            {
                map[u][v] = 0;
                map[v][u] = 1; // 양방향 통행으로 바꾸는 비용 발생
            }
            else // u -> v , v -> u 양방향 통행
            {
                map[u][v] = 0;
                map[v][u] = 0;
            }
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(map[i][k] != INF && map[k][j] != INF)
                    {
                        map[i][j] = Math.min(map[i][j], map[i][k]+ map[k][j]);
                    }
                }
            }
        }
        
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            sb.append(map[s][e]).append('\n');
        }
        System.out.print(sb);
    }
}
