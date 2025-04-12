package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1068
{
    static int rootNode, ans, erase;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        visit = new boolean[n];

        graph = new ArrayList[n];
        for(int i=0; i<n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int num = Integer.parseInt(st.nextToken());

            if(num == -1)
            {
                rootNode = i;
            }
            else
                graph[num].add(i);
        }

        erase = Integer.parseInt(br.readLine());

        if(erase == rootNode)
        {
            System.out.print(0);
        }
        else
        {
            dfs(rootNode);
            System.out.print(ans);
        }
    }

    static void dfs(int node)
    {
        visit[node] = true;

        boolean isLeaf = true;

        for(int next : graph[node])
        {
            if(!visit[next] && next != erase)
            {
                isLeaf = false;
                dfs(next);
            }
        }

        if(isLeaf) ans++;
    }
}
