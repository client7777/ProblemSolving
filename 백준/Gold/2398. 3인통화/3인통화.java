import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int n;
    static int m;
    static ArrayList<Node>[] graph;
    static ArrayList<Integer> position = new ArrayList<>();
    static int[][] dist;
    static int[][] path;
    static Set<String> route = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, cost));
            graph[v].add(new Node(u, cost));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++){
            position.add(Integer.parseInt(st.nextToken()));
        }

        path = new int[n+1][n+1];

        dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(dist[i], INF);
        }

        for(int node : position){
            dijkstra(node);
        }

        int minCost = INF;
        int meetingNode = -1;

        for(int i = 1; i <= n; i++){
            int totCost = 0;

            for(int start : position){
                totCost += dist[start][i];
            }

            if(totCost < minCost){
                minCost = totCost;
                meetingNode = i;
            }
        }

        for(int start : position){
            chasePath(start, meetingNode);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minCost).append(" ").append(route.size()).append('\n');

        for(String path : route){
            sb.append(path).append('\n');
        }

        System.out.print(sb);
    }

    static void dijkstra(int start){
        dist[start][start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();
            int curNode = cur.node;
            int curCost = cur.cost;

            if(curCost > dist[start][curNode]) continue;

            for(Node next : graph[curNode]){
                int nextNode = next.node;
                int nextCost = next.cost;

                if(dist[start][nextNode] > curCost + nextCost){
                    dist[start][nextNode] = curCost + nextCost;
                    path[start][nextNode] = curNode;
                    pq.add(new Node(nextNode, dist[start][nextNode]));
                }
            }
        }
    }

    static void chasePath(int start, int end){
        while (end != start){
            int prev = path[start][end];
            if(prev == 0) break;
            int u = Math.min(prev, end);
            int v = Math.max(prev, end);

            route.add(u + " " + v);
            end = prev;
        }
    }
    static class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
