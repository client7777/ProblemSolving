import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l;
    static int startNode;
    static int endNode;
    static ArrayList<Integer>[] station;
    static ArrayList<Integer>[] line;
    static boolean[] visitedLine;
    static boolean[] visitedStation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        visitedLine = new boolean[l+1];
        visitedStation = new boolean[n+1];

        station = new ArrayList[n+1];
        line = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            station[i] = new ArrayList<>();
            line[i] = new ArrayList<>();
        }

        for(int i = 1; i <= l; i++){
            st = new StringTokenizer(br.readLine());

            while (true){
                int stationNum = Integer.parseInt(st.nextToken());
                if(stationNum == -1) break;

                line[i].add(stationNum); // 호선에 속한 역
                station[stationNum].add(i); // 역이 지나는 호선
            }
        }

        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());

        System.out.print(bfs());
    }

    static int bfs(){

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int line : station[startNode]){
            visitedLine[line] = true;
            pq.add(new Node(line, startNode, 0));
        }

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.station == endNode){
                return cur.trans;
            }

            for(int nextStation : line[cur.line]){
                
                if(!visitedStation[nextStation]){
                    visitedStation[nextStation] = true;
                    pq.add(new Node(cur.line, nextStation, cur.trans));

                    for(int nextLine : station[nextStation]){
                        if(!visitedLine[nextLine]){
                            visitedLine[nextLine] = true;
                            pq.add(new Node(nextLine, nextStation, cur.trans + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node>{
        int line;
        int station;
        int trans;

        public Node(int line, int station, int trans) {
            this.line = line;
            this.station = station;
            this.trans = trans;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.trans, o.trans);
        }
    }
}
