package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_24479
{
    static int cnt = 1;
    static boolean[] visit;
    static int[] order;
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

        visit = new boolean[n+1];
        order = new int[n+1];

        for(int i=1; i<=n; i++)
        {
            //인접 정점은 오름차순으로 방문
            adjList[i].sort(Comparator.comparingInt(o->o));
        }
        
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(order[i]).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int start)
    {
        order[start] = cnt++;
        visit[start] = true;

        for(int next:adjList[start])
        {
            if(!visit[next]) dfs(next);
        }
    }
}
