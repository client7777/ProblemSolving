import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Integer>[] graph;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Position[] positions = new Position[n+1];

        StringTokenizer st;
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            positions[i] = new Position(x,y,p);
        }

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= n; i++){
            for(int j = i + 1; j <= n; j++){

                Position cowA = positions[i];
                Position cowB = positions[j];

                double dist = Math.sqrt(Math.pow(cowA.x - cowB.x, 2) + Math.pow(cowA.y - cowB.y, 2));

                if(cowA.p >= dist){
                    graph[i].add(j);
                }

                if(cowB.p >= dist){
                    graph[j].add(i);
                }
            }
        }

        for(int i = 1; i <= n; i++){
            bfs(i);
        }

        System.out.print(max);
    }

    static void bfs(int start){
        int cnt = 1;
        boolean[] visit = new boolean[n+1];
        visit[start] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()){
            Integer cur = q.poll();

            for(int next : graph[cur]){

                if(visit[next]) continue;
                visit[next] = true;
                q.add(next);
                cnt++;
            }
        }

        max = Math.max(max, cnt);
    }

    static class Position{
        int x;
        int y;
        int p;

        public Position(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }
}
