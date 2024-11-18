package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_5567
{
    static int n,m;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }
        System.out.print(bfs(1));
    }
    static int bfs(int start)
    {
        int cnt = 0;
        boolean[] visit = new boolean[n+1]; // 학번은 1번부터 n번까지
        int[] dist = new int[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:list[cur])
            {
                if(!visit[next])
                {
                    visit[next] = true;
                    dist[next] = dist[cur] + 1;
                    if(dist[next] <= 2)
                    {
                        q.add(next);
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
