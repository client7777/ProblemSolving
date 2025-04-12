package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_14267
{
    static ArrayList<Integer>[] graph;
    static int[] cost;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        cost = new int[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            int p = Integer.parseInt(st.nextToken());

            if(p == -1) continue;

            graph[p].add(i);
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            cost[node] += val;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(cost[i]).append(" ");
        }

        System.out.print(sb);
    }

    static void dfs(int node)
    {
        for(int next : graph[node])
        {
            cost[next] += cost[node];
            dfs(next);
        }
    }
}
