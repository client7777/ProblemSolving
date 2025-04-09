import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n;
    static int a,b,k,g;
    static int[] king;
    static ArrayList<Node>[] graph;
    static HashMap<String, block> blockTable = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()); // 시작 노드
        b = Integer.parseInt(st.nextToken()); // 목적지 노드
        k = Integer.parseInt(st.nextToken()); // 시간차
        g = Integer.parseInt(st.nextToken()); //고둘라가 방문하는 교차로의 개수

        king = new int[g];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<g; i++)
        {
            king[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,l));
            graph[v].add(new Node(u,l));
        }

        int curTime = 0;
        for(int i=0; i<g-1; i++)
        {
            int u = king[i];
            int v = king[i+1];

            int len = -1;
            for(Node node : graph[u])
            {
                if(node.node == v)
                {
                    len = node.l;
                    break;
                }
            }

            if(len == -1) continue;

            blockTable.put(u + " " + v , new block(curTime, curTime + len));
            blockTable.put(v + " " + u , new block(curTime, curTime + len));
            curTime += len;
        }

        dijkstra();
    }

    static void dijkstra()
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[a] = k;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(a, k));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curL = cur.l;

            if(curL > dist[curNode]) continue;

            for(Node next : graph[curNode])
            {
                int nextNode = next.node;
                int nextL = next.l;
                int arriveTime = curL;

                String key = curNode + " " + nextNode;

                if(blockTable.containsKey(key))
                {
                    block blockTime = blockTable.get(key);

                    if(arriveTime >= blockTime.startTime && arriveTime < blockTime.endTime)
                        arriveTime = blockTime.endTime;
                }

                nextL += arriveTime;

                if(dist[nextNode] > nextL)
                {
                    dist[nextNode] = nextL;
                    pq.add(new Node(nextNode, nextL));
                }
            }
        }

        System.out.print(dist[b] - k);
    }

    static class Node implements Comparable<Node>
    {
        int node;
        int l;

        public Node(int node, int l)
        {
            this.node = node;
            this.l = l;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.l, o.l);
        }
    }

    static class block
    {
        int startTime;
        int endTime;

        public block(int startTime, int endTime)
        {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
