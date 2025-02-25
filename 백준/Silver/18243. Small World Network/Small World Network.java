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
        int k = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;
                graph[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], 1);
            graph[b][a] = Math.min(graph[b][a], 1);
        }

        for(int l=1; l<=n; l++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    graph[i][j] = Math.min(graph[i][j], graph[i][l] + graph[l][j]);
                }
            }
        }

        boolean flag = true;

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(graph[i][j] > 6)
                {
                    flag = false;
                    break;
                }
            }
        }

        System.out.print(flag ? "Small World!" : "Big World!");
    }
}
