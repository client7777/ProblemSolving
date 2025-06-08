import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int target;
    static int[] d = {10, 60, 600};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(":");
        target = Integer.parseInt(input[0]) * 60 + Integer.parseInt(input[1]);

        bfs();
    }

    static void bfs(){
        boolean[][] visit = new boolean[3601][2];
        visit[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,0));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int curNode = cur.node;
            int curState = cur.state;
            int curCnt = cur.cnt;

            if(curNode == target){
                if(curState == 1){
                    System.out.print(curCnt);
                }
                else {
                    System.out.print(curCnt + 1);
                }
                
                return;
            }

            for(int next : d){
                int nextNode = curNode + next;

                if(OOB(nextNode) || visit[nextNode][curState]){
                    continue;
                }
                visit[nextNode][curState] = true;
                q.add(new Node(nextNode,curState,curCnt + 1));
            }

            if(curState == 1){
                int nextNode = curNode + 30;
                if(OOB(nextNode) || visit[nextNode][curState]){
                    continue;
                }

                visit[nextNode][curState] = true;
                q.add(new Node(nextNode,curState,curCnt + 1));
            }
            else {
                int nextNode = curNode == 0 ? 30 : curNode + 30;

                if(OOB(nextNode) || visit[nextNode][1]){
                    continue;
                }

                visit[nextNode][1] = true;
                q.add(new Node(nextNode,1,curCnt + 1));
            }
        }
    }

    static boolean OOB(int time){
        return time > 3600;
    }

    static class Node{
        int node;
        int state;
        int cnt;

        public Node(int node, int state, int cnt) {
            this.node = node;
            this.state = state;
            this.cnt = cnt;
        }
    }
}
