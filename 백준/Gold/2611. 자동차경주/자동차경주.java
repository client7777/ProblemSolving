import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static ArrayList<int[]>[] graph;
    static int[] inDegree, prev, d;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        inDegree = new int[n+1];
        prev = new int[n+1];
        d = new int[n+1];

        for(int i=1; i<=n; i++)
        {
            prev[i] = i;
            d[i] = 0;
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new int[]{q,r});
            inDegree[q]++;
        }

        topologicalSort();

        ArrayList<Integer> route = new ArrayList<>();
        route.add(1);

        int cur = prev[1];

        while (cur != 1)
        {
            route.add(cur);
            cur = prev[cur];
        }

        route.add(1);

        StringBuilder sb = new StringBuilder();
        sb.append(d[1]).append('\n');

        Collections.reverse(route);
        for(int node:route)
        {
            sb.append(node).append(" ");
        }

        System.out.print(sb);
    }

    static void topologicalSort()
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int[] next:graph[cur])
            {
                int nextNode = next[0];
                int nextScore = next[1];

                if(d[nextNode] < d[cur] + nextScore)
                {
                    d[nextNode] = d[cur] + nextScore;
                    prev[nextNode] = cur;
                }

                inDegree[nextNode]--;
                if(inDegree[nextNode] == 0)
                {
                    if(nextNode == 1) return;

                    q.add(nextNode);
                }
            }
        }
    }
}
