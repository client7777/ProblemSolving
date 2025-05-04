import java.io.*;
import java.util.*;

public class Main
{
    static int n, cnt;
    static int[] graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            n = Integer.parseInt(br.readLine());
            graph = new int[n+1];
            visit = new boolean[n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++)
            {
                graph[i] = Integer.parseInt(st.nextToken());
            }

            cnt = 0;
            for(int i=1; i<=n; i++)
            {
                if(!visit[i])
                {
                    cnt++;
                    dfs(i);
                }
            }

            sb.append(cnt).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int node)
    {
        visit[node] = true;

        int nextNode = graph[node];

        if(visit[nextNode]) return;
        visit[nextNode] = true;
        dfs(nextNode);
    }
}
