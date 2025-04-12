package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_15681
{
    static ArrayList<Integer>[] graph;
    static int[] size;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        size = new int[n+1];
        visit = new boolean[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(r);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++)
        {
            int query = Integer.parseInt(br.readLine());
            sb.append(size[query]).append('\n');
        }

        System.out.print(sb);
    }

    static int dfs(int node)
    {
        visit[node] = true;
        size[node] = 1;

        for(int next:graph[node])
        {
            if(visit[next]) continue;

            size[node] += dfs(next);
        }

        return size[node];
    }
}
