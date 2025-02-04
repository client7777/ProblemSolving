package Graph;
//모든 노드들과 직,간접적으로 연결이 되어있어야 정확한 키 순서를 알 수 있다.
import java.io.*;
import java.util.StringTokenizer;

public class boj_2458
{
    static int n,m;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[][] graph = new boolean[n+1][n+1];

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = true;
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(graph[i][k] && graph[k][j])
                    {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int[] ans = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(graph[i][j] || graph[j][i])
                    ans[i]++;
            }
        }

        int res = 0;
        for(int val:ans)
        {
            if(val == n - 1) res++;
        }

        System.out.print(res);
    }
}
