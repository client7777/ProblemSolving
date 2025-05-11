import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int r;
    static int d;
    static int x;
    static int y;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        Position[] positions = new Position[n+1];
        positions[0] = new Position(x,y);

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int posX = Integer.parseInt(st.nextToken());
            int posY = Integer.parseInt(st.nextToken());

            positions[i] = new Position(posX, posY);
        }

        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i <= n; i++){
            for(int j = i+1; j <= n; j++){
                Position node1 = positions[i];
                Position node2 = positions[j];

                double dist = getDistance(node1.x, node1.y, node2.x, node2.y);

                if(dist > r){
                    continue;
                }

                graph[i].add(j);
                graph[j].add(i);
            }
        }

        System.out.print(bfs());
    }

    static double bfs(){
        double totalDamage = 0.0;

        boolean[] visit = new boolean[n+1];
        visit[0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));

        while (!q.isEmpty()){
            Node cur = q.poll();
            int curNode = cur.node;
            int curStep = cur.step;

            for(int next : graph[curNode]){

                if(visit[next]){
                    continue;
                }

                visit[next] = true;
                totalDamage += (d / Math.pow(2, curStep));
                q.add(new Node(next, curStep + 1));
            }
        }

        return totalDamage;
    }

    static double getDistance(int x1, int y1, int x2 ,int y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    static class Node{
        int node;
        int step;

        public Node(int node, int step) {
            this.node = node;
            this.step = step;
        }
    }

    static class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
