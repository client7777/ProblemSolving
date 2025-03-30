package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_24484
{
    static int visit_order = 1;
    static boolean[] visit;
    static long[] depth;
    static long[] order;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
            adjList[v].add(u);
        }

        for(int i=1; i<=n; i++)
        {
            adjList[i].sort(Comparator.comparingInt(o->-o));
        }

        visit = new boolean[n+1];
        depth = new long[n+1];
        order = new long[n+1];

        Arrays.fill(depth, -1);

        dfs(r, 0);

        long sum = 0;
        for(int i=1; i<=n; i++)
        {
            sum += (depth[i] * order[i]);
        }

        System.out.print(sum);
    }

    static void dfs(int node, int level)
    {
        depth[node] = level;
        visit[node] = true;
        order[node] = visit_order++;

        for(int next:adjList[node])
        {
            if(!visit[next])
            {
                dfs(next, level + 1);
            }
        }
    }
}
