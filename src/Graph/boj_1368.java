package Graph;
// 초기에는 모든 논이 물을 대지 않은 상태이기 때문에 무조건 한번은 논에 우물을 파야한다.
// 가상의 노드를 설정해서 그래프를 변형시킴
import java.util.*;
import java.io.*;

public class boj_1368
{
    static int n; // 논의 수
    static ArrayList<int[]> graph = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=0; i<=n; i++)
        {
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            // 가상의 노드 0번 노드를 사용
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken()); // i번째 노드에 우물을 파는 비용
            graph.add(new int[]{0,i,cost});
        }
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                int cost = Integer.parseInt(st.nextToken());
                if(i == j) continue;
                graph.add(new int[]{i,j,cost});
            }
        }
        graph.sort(Comparator.comparingInt(o->o[2]));
        kruskal();
    }
    static void kruskal()
    {
        int used = 0;
        int totCost = 0;
        for(int[] edge:graph)
        {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            if(find(from) != find(to))
            {
                union(from, to);
                totCost += cost;
                used++;
            }
            if(used == n) break; // 노드가 총 n+1개 이므로 MST를 이루기 위해서는 총 n개의 간선이 필요
        }
        System.out.print(totCost);
    }
    static int find(int x)
    {
        if(x == parent[x])
        {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }
}
/*
public class boj_1368
{
    static int n;
    static ArrayList<int[]>[] graph;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
    public static void main(String[] args) throws IOException
    {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());

       graph = new ArrayList[n+1];
       for(int i=0; i<=n; i++)
       {
           graph[i] = new ArrayList<>();
       }
       for(int i=1; i<=n; i++)
       {
           int cost = Integer.parseInt(br.readLine());
           graph[0].add(new int[]{i,cost});
           graph[i].add(new int[]{0,cost});
       }
       for(int i=1; i<=n; i++)
       {
           StringTokenizer st = new StringTokenizer(br.readLine());
           for(int j=1; j<=n; j++)
           {
               int cost = Integer.parseInt(st.nextToken());
               graph[i].add(new int[]{j,cost});
               graph[j].add(new int[]{i,cost});
           }
       }
       prim();
    }
    static void prim()
    {
        int tot = 0;
        boolean[] visit = new boolean[n+1];
        visit[0] = true;
        // 0번노드에서 시작하는 간선을 모두 큐에 추가
        // 초기에는 물을 댄 논이 없으므로 논에 우물을 직접 파야함 -> 0번 노드에서부터 탐색을 시작해야 함
        for(int[] edge:graph[0])
        {
            pq.add(edge);
        }
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int next = cur[0];
            int cost = cur[1];
            if(!visit[next])
            {
                visit[next] = true;
                tot += cost;

                for(int[] edge:graph[next])
                {
                    pq.add(edge);
                }
            }
        }
        System.out.print(tot);
    }
}
*/