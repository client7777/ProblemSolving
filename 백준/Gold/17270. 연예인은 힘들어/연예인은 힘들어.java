import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int v;
    static final int INF = 1_000_000_000;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];
        for(int i=1; i<=v; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //양방향 그래프
            graph[a].add(new int[]{b,c});
            graph[b].add(new int[]{a,c});
        }

        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] startFromJ = dijkstra(j);
        int[] startFromS = dijkstra(s);

        int minDist = INF;
        for(int i=1; i<=v; i++)
        {
            if(i == j || i == s) continue;
            minDist = Math.min(minDist, startFromJ[i] + startFromS[i]);
        }

        ArrayList<Integer> candidateNode = new ArrayList<>();
        for(int i=1; i<=v; i++)
        {
            if(i == j || i == s) continue;
            if(startFromJ[i] + startFromS[i] == minDist)
            {
                if(startFromJ[i] <= startFromS[i]) candidateNode.add(i);
            }
        }

        int ans = -1;
        int dist = INF;
        for(int index:candidateNode)
        {
            if(dist > startFromJ[index])
            {
                ans = index;
                dist = startFromJ[index];
            }
        }

        System.out.print(ans);
    }

    static int[] dijkstra(int start)
    {
        int[] dist = new int[v+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNoe = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNoe]) continue;

            for(int[] next:graph[curNoe])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[nextNode] > dist[curNoe] + nextDist)
                {
                    dist[nextNode] = dist[curNoe] + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }

        }
        return dist;
    }

    static class Node implements Comparable<Node>
    {
        int node;
        int dist;

        public Node(int node, int dist)
        {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}