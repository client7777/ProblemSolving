package Graph;

import java.io.*;
import java.util.*;

public class boj_14574
{
    static int N;
    static int[] parent;
    static boolean[] visited;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> -a[2]));
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for(int i=1; i<=N; i++)
        {
            parent[i] = i;
        }
        visited = new boolean[N + 1];

        int[][] value = new int[N+1][3];
        StringTokenizer st;
        for (int i = 1; i <= N; i++)
        {
            st = new StringTokenizer(br.readLine());
            value[i][0] = i;
            value[i][1] = Integer.parseInt(st.nextToken());
            value[i][2] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N - 1; i++)
        {
            for (int j = i + 1; j <= N; j++)
            {
                int floor = (value[i][2] + value[j][2]) / Math.abs(value[i][1] - value[j][1]);
                pq.add(new int[]{value[i][0], value[j][0], floor});
            }
        }
        long sum = kruskal();
        System.out.println(sum);
        dfs(1);
        br.close();
    }

    // 재귀 호출 -> 출력 : 다음 노드를 탐색한 후에 현재 노드가 어떤 경로로 다음 노드에 도달했는지, 탐색 후 경로를 출력
    // 출력 -> 재귀호출 : 현재 노드에서 다음 노드로 이동하는 모든 경로를 나타냄, 이동하기 전 경로를 출력
    static void dfs(int cur)
    {
        visited[cur] = true;
        for (Integer next : graph[cur])
        {
            if (visited[next]) continue;

            dfs(next);
            System.out.println(cur + " " + next);
        }
    }

    static int find(int x)
    {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }

    static long kruskal()
    {
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
        {
            graph[i] = new ArrayList<>();
        }
        int selected = 0;
        long sum = 0;

        while (!pq.isEmpty() && selected < N - 1) {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int cost = cur[2];

            if(find(from) == find(to)) continue;

            union(from, to);
            selected++;
            sum += cost;
            graph[from].add(to);
            graph[to].add(from);
        }

        return sum;
    }

}