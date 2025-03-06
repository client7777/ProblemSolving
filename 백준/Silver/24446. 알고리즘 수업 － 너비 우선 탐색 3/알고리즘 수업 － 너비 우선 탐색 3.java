import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int[] depth;
    static boolean[] visit;
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

        depth = new int[n+1];
        Arrays.fill(depth, -1);
        visit = new boolean[n+1];
        bfs(r);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(depth[i]).append('\n');
        }

        System.out.print(sb);
    }

    static void bfs(int start)
    {
        visit[start] = true;
        depth[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int next:adjList[cur])
            {
                if(!visit[next])
                {
                    visit[next] = true;
                    q.add(next);
                    depth[next] = depth[cur] + 1;
                }
            }
        }
    }
}
