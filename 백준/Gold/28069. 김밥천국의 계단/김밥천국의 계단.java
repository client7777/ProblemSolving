import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.print(bfs(n, k) ? "minigimbob" : "water");
    }

    static boolean bfs(int n, int k) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));

        boolean[] visit = new boolean[n + 1];
        visit[0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int curNode = cur.node;
            int curStep = cur.step;

            if (curNode == n && curStep <= k) {
                return true;
            }

            if (curStep > k) {
                continue;
            }

            int nextNode = curNode + 1;

            if (nextNode <= n && !visit[nextNode]) {
                visit[nextNode] = true;
                q.add(new Node(nextNode, curStep + 1));
            }

            nextNode = curNode + (curNode / 2);
            if (nextNode <= n && !visit[nextNode]) {
                visit[nextNode] = true;
                q.add(new Node(nextNode, curStep + 1));
            }
        }

        return false;
    }

    static class Node {
        int node;
        int step;

        public Node(int node, int step) {
            this.node = node;
            this.step = step;
        }
    }
}
