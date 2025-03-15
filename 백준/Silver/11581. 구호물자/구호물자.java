import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static boolean hasCycle = false;
    static ArrayList<Integer>[] graph;
    static int[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1; i<=n-1; i++)
        {
            int m = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        visit = new int[n+1];
        dfs(1);
        System.out.print(hasCycle ? "CYCLE" : "NO CYCLE");
    }

    static void dfs(int node)
    {
        if(visit[node] == 1)
        {
            hasCycle = true;
            return;
        }

        if(visit[node] == 2) return;

        visit[node] = 1;

        for(int next:graph[node])
        {
            dfs(next);
        }

        visit[node] = 2;
    }
}