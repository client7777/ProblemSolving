import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main
{
    static int ans = 0;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visit = new boolean[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[b].add(a);
        }

        int start = Integer.parseInt(br.readLine());

        visit[start] = true;

        dfs(start);

        System.out.print(ans);
    }

    static void dfs(int node)
    {
        for(int next:graph[node])
        {
            if(!visit[next])
            {
                visit[next] = true;
                ans++;
                dfs(next);
            }
        }
    }
}
