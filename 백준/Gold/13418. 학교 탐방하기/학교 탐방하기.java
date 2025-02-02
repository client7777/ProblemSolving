import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;
    static ArrayList<int[]> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        // m+1개의 간선 정보 입력 (시작 지점 포함)
        for (int i = 0; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new int[]{a, b, c});
        }

        // 최악의 경로 - 최선의 경로 출력
        System.out.print(worst() - best());
    }

    // 최악의 경로 (오름차순 정렬)
    static int worst() {
        // 부모 배열 초기화
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        // 오름차순 정렬 (가중치 기준)
        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        int up = 0;
        int used = 0;

        // 간선 선택
        for (int[] edge : edges) {
            if (used >= n) break;
            int from = edge[0];
            int to = edge[1];
            int type = edge[2];

            // 사이클이 발생하지 않으면 간선 추가
            if (find(from) != find(to)) {
                union(from, to);
                used++;
                if (type == 0) up++;  // 오르막길일 경우
            }
        }
        return up * up;
    }

    // 최선의 경로 (내림차순 정렬)
    static int best() {
        // 부모 배열 초기화
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        // 내림차순 정렬 (가중치 기준)
        Collections.sort(edges, (o1, o2) -> o2[2] - o1[2]);

        int up = 0;
        int used = 0;

        // 간선 선택
        for (int[] edge : edges) {
            if (used >= n) break;
            int from = edge[0];
            int to = edge[1];
            int type = edge[2];

            // 사이클이 발생하지 않으면 간선 추가
            if (find(from) != find(to)) {
                union(from, to);
                used++;
                if (type == 0) up++;  // 오르막길일 경우
            }
        }
        return up * up;
    }

    // 두 정점을 하나의 집합으로 병합
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    // 정점의 루트를 찾음 (경로 압축)
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
