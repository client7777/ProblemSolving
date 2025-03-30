import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static ArrayList<Integer>[] graph;
    static boolean flag = false;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }

        int s = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<s; i++)
        {
            set.add(Integer.parseInt(st.nextToken()));
        }
        
        dfs(1);
        
        System.out.print(flag ? "yes" : "Yes");
    }

    static void dfs(int node)
    {
        if(set.contains(node) || flag) return;
        
        if(graph[node].isEmpty())
        {
            flag = true;
            return;
        }
        
        for(int next:graph[node]) dfs(next);
    }
}
