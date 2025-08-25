package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_22944 {
    static int n;
    static int h;
    static int d;
    static char[][] map;
    static int startX;
    static int startY;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = row.charAt(j);

                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        System.out.print(bfs());
    }

    static int bfs(){
        int min = Integer.MAX_VALUE;

        int[][] visit = new int[n][n];
        visit[startX][startY] = h;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0, h, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for(int dir = 0; dir < 4; dir++){
                int nX = cur.x + dx[dir];
                int nY = cur.y + dy[dir];
                int nCnt = cur.cnt + 1;
                int nHp = cur.hp;
                int nUmbrella = cur.umbrella;

                if(OOB(nX,nY)) continue;

                if(map[nX][nY] == 'E'){
                    min = Math.min(min, cur.cnt + 1 );
                }

                if(map[nX][nY] == 'U'){
                    nUmbrella = d;
                }

                if(nUmbrella > 0){
                    nUmbrella--;
                }
                else{
                    nHp--;
                }

                if(nHp <= 0){
                    continue;
                }

                if(visit[nX][nY] < nHp + nUmbrella){
                    visit[nX][nY] = nHp + nUmbrella;
                    q.add(new Node(nX,nY,nCnt, nHp, nUmbrella));
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }


    static boolean OOB(int x,int y){
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    static class Node{
        int x;
        int y;
        int cnt;
        int hp;
        int umbrella;

        public Node(int x, int y, int cnt, int hp, int umbrella) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.hp = hp;
            this.umbrella = umbrella;
        }
    }
}
