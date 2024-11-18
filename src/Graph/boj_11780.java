package Graph;
// 플로이드 알고리즘, 경로 복원
import java.io.*;
import java.util.*;

public class boj_11780
{
    static int n,m;
    static int[][] ans,next;
    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        ans = new int[n+1][n+1];
        next = new int[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i != j)
                {
                    ans[i][j] = MAX;
                }
            }
        }
        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            ans[u][v] = Math.min(ans[u][v],w);
            next[u][v] = v;
        }
        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(ans[i][k] != MAX && ans[k][j] != MAX && ans[i][j] > ans[i][k] + ans[k][j])
                    {
                        ans[i][j] = ans[i][k] + ans[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                System.out.print((ans[i][j] == MAX ? 0 : ans[i][j]) + " ");
            }
            System.out.println();
        }
        //최단경로 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(ans[i][j] == 0 || ans[i][j] == MAX)
                {
                    sb.append(0).append('\n');
                    continue;
                }
                ArrayList<Integer> path = new ArrayList<>();
                int start = i;
                while (start != j)
                {
                    path.add(start);
                    start = next[start][j];
                }
                path.add(j);
                sb.append(path.size() + " ");
                for(int node:path)
                {
                    sb.append(node + " ");
                }
                sb.append('\n');
            }
        }
        System.out.print(sb);
    }
}
