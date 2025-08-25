package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_5917 {
    static final int INF = 987654321;
    static int n;
    static int m;
    static ArrayList<Node>[] graph;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v,d));
            graph[v].add(new Node(u,d));
            edges.add(new Edge(u,v,d));
        }

        int[] origin = dijkstra(-1,-1,-1);
        int baseDist = origin[n];
        int max = 0;

        for(Edge e : edges){

            int u = e.u;
            int v = e.v;
            int d = e.d;

            int[] newDist = dijkstra(u,v,d);
            int compareDist = newDist[n];

            max = Math.max(max, compareDist - baseDist);
        }

        System.out.print(max);
    }

    static int[] dijkstra(int u, int v, int d){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;

            if(curDist > dist[curNode]){
                continue;
            }

            for(Node next : graph[curNode]){
                int nextNode = next.node;
                int nextDist = next.dist;

                if ((curNode == u && nextNode == v && next.dist == d) ||
                        (curNode == v && nextNode == u && next.dist == d)) {
                    nextDist *= 2;
                }

                if(dist[nextNode] > curDist + nextDist){
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }

        return dist;
    }

    static class Edge{
        int u;
        int v;
        int d;

        public Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }
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
