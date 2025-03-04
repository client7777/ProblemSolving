import java.io.*;
import java.util.*;

public class Main
{
    static int n,m,x;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<int[]>[] adjList,reverseAdjList;
    static int[] dist, reverseDist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        reverseAdjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }

        dist = new int[n+1];
        reverseDist = new int[n+1];
        Arrays.fill(dist, INF);
        Arrays.fill(reverseDist, INF);

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,c});
            reverseAdjList[v].add(new int[]{u,c});
        }
        //단방향 간선의 방향을 역으로 뒤집음: 파티에 참석하러 가는 최단 거리
        //각 출발 노드에서 목적지 노드로 향하는 간선의 방향을 역으로 뒤집어서 목적지 노드를 출발노드로 취급
        dijkstra(x, reverseDist, reverseAdjList);
        //단방향 간선 방향 그대로: 파티가 끝나고 집에 복귀하는 최단 거리
        dijkstra(x, dist, adjList);
        int max = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, dist[i] + reverseDist[i]);
        }
        System.out.print(max);
    }
    static void dijkstra(int start, int[] arr, ArrayList<int[]>[] graph)
    {
        arr[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0});
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            if(curCost > arr[curNode]) continue;
            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];
                if(arr[nextNode] > arr[curNode] + nextCost)
                {
                    arr[nextNode] = arr[curNode] + nextCost;
                    pq.add(new int[]{nextNode, arr[nextNode]});
                }
            }
        }
    }
}
