import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int s;
    static int d;
    static int f;
    static int b;
    static int k;
    static HashSet<Integer> policeStation = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(k > 0){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++){
                int position = Integer.parseInt(st.nextToken());
                policeStation.add(position);
            }
        }

        int answer = bfs();
        System.out.print(answer == -1 ? "BUG FOUND" : answer);
    }

    static int bfs(){
        boolean[] visit = new boolean[n + 1];
        visit[s] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, 0));

        while (!q.isEmpty()){
            Node cur = q.poll();
            int curNode = cur.node;
            int curCnt = cur.cnt;

            if(curNode == d){
                return curCnt;
            }

            //후방으로 이동
            int backMove = curNode - b;
            if(backMove > 0 && !visit[backMove] && !policeStation.contains(backMove)){
                visit[backMove] = true;
                q.add(new Node(backMove, curCnt + 1));
            }

            //전방으로 이동
            int frontMove = curNode + f;
            if(frontMove <= n && !visit[frontMove] && !policeStation.contains(frontMove)){
                visit[frontMove] = true;
                q.add(new Node(frontMove, curCnt + 1));
            }

        }

        return -1;
    }

    static class Node{
        int node;
        int cnt;

        public Node(int node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
}
