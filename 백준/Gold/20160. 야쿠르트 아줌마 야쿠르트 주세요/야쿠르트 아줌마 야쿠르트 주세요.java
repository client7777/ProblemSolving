import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v;
    static int e;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v+1];
        for(int i = 1; i <= v; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        int[] visitOrder = new int[10];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 10; i++){
            visitOrder[i] = Integer.parseInt(st.nextToken());
        }

        long[] time = new long[10];
        Arrays.fill(time, Long.MAX_VALUE);
        time[0] = 0;

        int curVisit = visitOrder[0];
        int curIdx = 0;

        for(int i = 1; i < 10; i++){
            int nextVisit = visitOrder[i];
            int minDist = dijkstra(curVisit)[nextVisit];

            if(minDist == Integer.MAX_VALUE){
                continue;
            }

            time[i] = time[curIdx] + minDist;
            curVisit = nextVisit;
            curIdx = i;
        }

        int startPosition = Integer.parseInt(br.readLine());
        int[] myPath = dijkstra(startPosition);

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 10; i++){
            int cur = visitOrder[i];

            if(time[i] != Long.MAX_VALUE && time[i] >= myPath[cur]){
                answer = Math.min(answer, cur);
            }
        }

        System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static int[] dijkstra(int start){
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNode]){
                continue;
            }

            for(Node next : graph[curNode]){
                int nextNode = next.node;
                int nextDist = curDist + next.dist;

                if(dist[nextNode] > nextDist){
                    dist[nextNode] = nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node>{
        int node;
        int dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
