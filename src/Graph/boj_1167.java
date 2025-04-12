package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1167
{
    static int max = 0;
    static int farNode = 0;
    static boolean[] visit;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v = Integer.parseInt(br.readLine());

        graph = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=0; i<v; i++)
        {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true)
            {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new int[]{to, cost});
            }
        }

        visit = new boolean[v+1];
        dfs(1, 0);

        max = 0;
        dfs(farNode, 0);

        System.out.print(max);
    }

    static void dfs(int node, int sum)
    {
        if(sum > max)
        {
            max = sum;
            farNode = node;
        }

        for(int[] next:graph[node])
        {
            int nextNode = next[0];
            int nextCost = next[1];

            if(!visit[nextNode])
            {
                visit[node] = true;
                dfs(nextNode, sum + nextCost);
                visit[node] = false;
            }
        }
    }
}
