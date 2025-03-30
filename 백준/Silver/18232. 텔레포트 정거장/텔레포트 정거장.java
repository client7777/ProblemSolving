import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visit = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        System.out.print(bfs(s,e));

    }

    static int bfs(int s, int e)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{s, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curNode == e) return curDist;

            if(OOB(curNode) || visit[curNode]) continue;

            visit[curNode] = true;

            q.add(new int[]{curNode -1, curDist + 1});
            q.add(new int[]{curNode +1, curDist + 1});

            for(int next : graph[curNode])
            {
                q.add(new int[]{next, curDist + 1});
            }
        }

        return -1;
    }

    static boolean OOB(int x)
    {
        return x < 1 || x > n;
    }
}
