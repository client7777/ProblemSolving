package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_4803
{
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        int test_Case = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            graph = new ArrayList[n+1];
            for(int i=1; i<=n; i++)
            {
                graph[i] = new ArrayList<>();
            }

            visit = new boolean[n+1];

            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            int tree = 0;

            for(int i=1; i<=n; i++)
            {
                if(visit[i]) continue;
                tree += bfs(i);
            }

            if(tree == 0)
            {
                sb.append("Case ").append(test_Case).append(": ").append("No trees.").append('\n');
            }
            else if(tree == 1)
            {
                sb.append("Case ").append(test_Case).append(": ").append("There is one tree.").append('\n');
            }
            else if(tree > 1)
            {
                sb.append("Case ").append(test_Case).append(": ").append("A forest of ").append(tree).append(" ").append("trees.").append('\n');
            }

            test_Case++;
        }

        System.out.print(sb);
    }

    static int bfs(int start)
    {
        int edge = 0; //간선
        int node = 0; //노드
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            if(visit[cur]) continue;

            visit[cur] = true;

            node++;

            for(int next:graph[cur])
            {
                q.add(next);
                edge++;
            }
        }

        return (edge / 2) + 1 == node ? 1 : 0;
    }
}
