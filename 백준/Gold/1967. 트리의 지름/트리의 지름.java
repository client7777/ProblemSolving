import java.io.*;
import java.util.*;

public class Main 
{
    static int n, maxDist, farthestNode;
    static ArrayList<int[]>[] graph;
    static boolean[] visit;

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) { // 노드가 하나인 경우, 지름은 0
            System.out.print(0);
            return;
        }

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[u].add(new int[]{v, cost});
            graph[v].add(new int[]{u, cost});
        }

        
        visit = new boolean[n + 1];
        dfs(1, 0);

        
        visit = new boolean[n + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.print(maxDist);
    }

    static void dfs(int node, int sum) {
        visit[node] = true;

        if (sum > maxDist) {
            maxDist = sum;
            farthestNode = node;
        }

        for (int[] next : graph[node]) {
            int nextNode = next[0], nextCost = next[1];
            if (!visit[nextNode]) dfs(nextNode, sum + nextCost);
        }
    }
}
