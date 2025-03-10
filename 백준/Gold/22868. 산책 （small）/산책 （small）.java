import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static ArrayList<Integer>[] graph;
    static int[] prev;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        prev = new int[n+1];
        visit = new boolean[n+1];

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
            graph[v].add(u);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
        {
            if(!graph[i].isEmpty())
            {
                Collections.sort(graph[i]);
            }
        }

        int ans = 0;
        ans += bfs(s,e);

        Arrays.fill(visit, false);
        ArrayList<Integer> path = new ArrayList<>();
        int chase = prev[e];
        while (chase != s)
        {
            path.add(chase);
            chase = prev[chase];
        }

        for(int val:path)
        {
            visit[val] = true;
        }

        ans += bfs(e,s);
        System.out.print(ans);

    }
    static int bfs(int start, int end)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curNode == end) return curDist;

            for(int next:graph[curNode])
            {
                if(visit[next]) continue;

                prev[next] = curNode;
                visit[next] = true;
                q.add(new int[]{next, curDist + 1});
            }
        }
        return -1;
    }
}
