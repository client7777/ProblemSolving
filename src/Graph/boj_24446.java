package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_24446
{
    static int[] depth;
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
        depth[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

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
                        depth[next] = level;
                        q.add(next);
                    }
                }
            }

            level++;
        }
    }
}
