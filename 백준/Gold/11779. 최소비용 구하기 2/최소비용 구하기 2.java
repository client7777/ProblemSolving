import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int[] pre;
    static final int INF = Integer.MAX_VALUE;
    static int startNode, endNode;
    static ArrayList<int[]>[] adjList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        pre = new int[n+1];

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,w});
        }

        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());

        dijkstra();

    }
    //다익스트라 알고리즘
    static void dijkstra()
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[startNode] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{startNode, 0});
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if(curCost > dist[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];
                if(dist[nextNode] > dist[curNode] + nextCost)
                {
                    dist[nextNode] = dist[curNode] + nextCost;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                    pre[nextNode] = curNode;
                }
            }
        }
        sb.append(dist[endNode]).append('\n');
        Stack<Integer> path = new Stack<>();
        int st = endNode;
        while (st != startNode)
        {
            path.add(st);
            st = pre[st];
        }
        path.add(startNode);
        sb.append(path.size()).append('\n');
        while (!path.isEmpty())
        {
            sb.append(path.pop() + " ");
        }
        System.out.print(sb);
    }
}
