package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16947
{
    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static boolean isCycle;
    static int[] dist;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        visit = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, -1);
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(0,1);
        bfs();
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(dist[i] + " ");
        }
        System.out.print(sb);
    }
    static void dfs(int prev, int cur)
    {
        visit[cur] = true;
        for(int next:graph[cur])
        {
            if(next == prev) continue;
            if(visit[next]) // 사이클 발견
            {
                isCycle = true;
                dist[next] = 0;
                q.add(next);
                break; // 사이클 발견시 탐색 종료
            }
            else // 방문하지 않은 노드라면
            {
                dfs(cur,next); // 더 깊이 탐색하기 위해 재귀호출
                if(isCycle) // 탐색중에 사이클 발견
                {
                    if(dist[next] == 0) // 이미 사이클의 일부로 설정된 상태라면
                    {
                        isCycle = false;
                    }
                    else
                    {
                        dist[next] = 0;
                        q.add(next);
                    }
                    return;
                }
            }
        }
    }
    static void bfs()
    {
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:graph[cur])
            {
                if(dist[next] == -1)
                {
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
    }
}

