import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static List<Integer>[] adjList;
    static int[] p;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 노드의 개수

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        p = new int[n+1];
        visit = new boolean[n+1];

        for(int i=0; i<n-1; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }
        bfs(1);
        for(int i=2; i<=n; i++)
        {
            sb.append(p[i]).append('\n');
        }
        System.out.print(sb);
    }
    static void bfs(int num)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        visit[num] = true;
        while(!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:adjList[cur])
            {
                if(!visit[next])
                {
                    q.add(next);
                    visit[next] = true;
                    p[next] = cur;
                }
            }
        }
    }
}
