import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import static java.lang.Integer.*;

public class Main {
    static int[] parent;
    static boolean[] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> compare(e2.w, e1.w));
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        parent = new int[N + 1];
        visited = new boolean[N + 1];

        int[] pValues = new int[N + 1];
        int[] cValues = new int[N + 1];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            pValues[i] = parseInt(st.nextToken());
            cValues[i] = parseInt(st.nextToken());
        }

        int w;
        for (int u = 1; u < pValues.length - 1; u++)
            for (int v = u + 1; v < pValues.length; v++) {
                w = (cValues[u] + cValues[v]) / Math.abs(pValues[u] - pValues[v]);
                pq.offer(new Edge(u, v, w));
            }


        long sum = kruskal(N);
        System.out.println(sum);
        dfs(1);
        br.close();
    }

    static void dfs(int cur) {
        visited[cur] = true;


        for (Integer next : graph.get(cur)) {
            if (visited[next]) continue;

            dfs(next);
            System.out.println(cur + " " + next);
        }
    }

    static int find(int u) {
        if (parent[u] < 0) return u;

        return parent[u] = find(parent[u]);
    }

    static boolean union(int u, int v) {
        int r1 = find(u);
        int r2 = find(v);

        if (r1 == r2) return false;

        if (parent[r1] < parent[r2]) {
            parent[r1] += parent[r2];
            parent[r2] = r1;
        } else {
            parent[r2] += parent[r1];
            parent[r1] = r2;
        }

        return true;
    }

    static long kruskal(int N) {
        Arrays.fill(parent, -1);
        graph = new ArrayList<>();
        graph.add(null);
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        int selected = 0;
        long sum = 0;

        while (!pq.isEmpty() && selected < N - 1) {
            Edge e = pq.poll();

            if (!union(e.u, e.v)) continue;

            selected++;
            sum += e.w;
            graph.get(e.u).add(e.v);
            graph.get(e.v).add(e.u);
        }

        return sum;
    }

    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}