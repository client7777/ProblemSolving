import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int KNIGHT = 0;
    static final int BISHOP = 1;
    static final int ROOK = 2;

    static int[] knightX = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] knightY = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] bishopX = {1, 1, -1, -1};
    static int[] bishopY = {1, -1, -1, 1};
    static int[] rookX = {-1, 0, 1, 0};
    static int[] rookY = {0, 1, 0, -1};

    static int n;
    static int startX;
    static int startY;
    static int[][] map;
    static boolean[][][][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1){
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs();
    }

    static void bfs(){
        visit = new boolean[n][n][3][101];

        Queue<Node> q = new LinkedList<>();

        for(int state = 0; state < 3; state++){
            visit[startX][startY][state][2] = true;
            q.add(new Node(startX, startY, state, 0, 2));
        }

        while (!q.isEmpty()){
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curState = cur.state;
            int curDist = cur.dist;
            int curVal = cur.val;

            if(curVal == n * n + 1){
                System.out.print(curDist);
                return;
            }

            for(int i = 0; i < 3; i++){
                if(curState == i || visit[curX][curY][i][curVal]){
                    continue;
                }

                visit[curX][curY][i][curVal] = true;
                q.add(new Node(curX, curY, i, curDist + 1, curVal));
            }

            switch (curState){
                //현재 상태가 NIGHT
                case KNIGHT :
                    for(int dir=0; dir < 8; dir++){
                        int nX = curX + knightX[dir];
                        int nY = curY + knightY[dir];

                        if(OOB(nX,nY) || visit[nX][nY][KNIGHT][curVal]){
                            continue;
                        }

                        visit[nX][nY][KNIGHT][curVal] = true;

                        if(map[nX][nY] == curVal){
                            q.add(new Node(nX,nY,KNIGHT,curDist + 1, curVal + 1));
                        }
                        else{
                            q.add(new Node(nX,nY,KNIGHT,curDist + 1, curVal));
                        }
                    }

                    break;

                case BISHOP :
                    for(int dir=0; dir<4; dir++){
                        int nX = curX + bishopX[dir];
                        int nY = curY + bishopY[dir];

                        while (true){
                            if(OOB(nX,nY)) break;

                            if(!visit[nX][nY][BISHOP][curVal]) {

                                visit[nX][nY][BISHOP][curVal] = true;

                                if(map[nX][nY] == curVal){
                                    q.add(new Node(nX,nY,BISHOP,curDist + 1, curVal + 1));
                                }
                                else {
                                    q.add(new Node(nX,nY,BISHOP, curDist + 1, curVal));
                                }
                            }

                            nX += bishopX[dir];
                            nY += bishopY[dir];
                        }
                    }
                    break;

                case ROOK :
                    for(int dir=0; dir<4; dir++){
                        int nX = curX + rookX[dir];
                        int nY = curY + rookY[dir];

                        while (true){
                            if(OOB(nX,nY)) break;
                            if(!visit[nX][nY][ROOK][curVal]) {

                                visit[nX][nY][ROOK][curVal] = true;

                                if(map[nX][nY] == curVal){
                                    q.add(new Node(nX,nY,ROOK,curDist + 1, curVal + 1));
                                }
                                else {
                                    q.add(new Node(nX,nY,ROOK, curDist + 1, curVal));
                                }

                            }
                            nX += rookX[dir];
                            nY += rookY[dir];
                        }
                    }

                    break;
            }
        }
    }

    static boolean OOB(int x, int y){
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    static class Node{
        int x;
        int y;
        int state;
        int dist;
        int val;

        public Node(int x, int y, int state, int dist, int val) {
            this.x = x;
            this.y = y;
            this.state = state;
            this.dist = dist;
            this.val = val;
        }
    }
}
