import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
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
            adjList[i].sort(Comparator.comparingInt(o->o));
        }

        depth = new long[n+1];
        Arrays.fill(depth ,-1);
        order = new long[n+1];

        bfs(r);

        long sum = 0;
        for(int i=1; i<=n; i++)
        {
            sum += (depth[i] * order[i]);
        }
        
        System.out.print(sum);
    }

    static void bfs(int start)
    {
        depth[start] = 0;
        order[start] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int visit_order = 2;
        int level = 1;

        while (!q.isEmpty())
        {
            int size = q.size();

            for(int i=0; i<size; i++)
            {
                int cur = q.poll();

                for(int next:adjList[cur])
                {
                    if(depth[next] == -1)
                    {
                        q.add(next);
                        order[next] = visit_order++;
                        depth[next] = level;
                    }
                }
            }

            level++;
        }
    }
}
