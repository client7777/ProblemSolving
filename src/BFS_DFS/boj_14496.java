package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_14496
{
    static int a,b;
    static int n,m;
    static ArrayList<Integer>[] adjList;
    static int[] dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

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
        System.out.print(dijkstra(a));
    }
    static int dijkstra(int start)
    {
        dist[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int next:adjList[cur])
            {
                if(dist[next] > dist[cur] + 1)
                {
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
        return dist[b] == Integer.MAX_VALUE ? -1 : dist[b];
    }
}

/*
static int bfs(int start)
    {
        boolean[] visit = new boolean[n+1];
        visit[start] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if(curNode == b)
            {
                return curCost;
            }

            for(int next:adjList[curNode])
            {
                if(!visit[next])
                {
                    q.add(new int[]{next, curCost + 1});
                    visit[next] = true;
                }
            }
        }
        return -1;
    }
*/
