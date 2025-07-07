import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0,- 1};
    static boolean[][] visited;
    static int largestArea = Integer.MIN_VALUE;
    static int minPerimeter = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            String row = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = row.charAt(j);
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == '#' && !visited[i][j]){
                    bfs(i, j);
                }
            }
        }

        System.out.print(largestArea + " " + minPerimeter);
    }
    
    static void bfs(int x, int y){
        int area = 1;
        int perimeter = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nX = cur.x + dx[dir];
                int nY = cur.y + dy[dir];

                if (OOB(nX, nY) || map[nX][nY] == '.') {
                    perimeter++;  // 격자 밖이거나 빈 공간일 경우 둘레 증가
                } else if (map[nX][nY] == '#' && !visited[nX][nY]) {
                    visited[nX][nY] = true;
                    q.add(new Node(nX, nY));
                    area++;
                }
            }
        }

        if (area > largestArea) {
            largestArea = area;
            minPerimeter = perimeter;
        } else if (area == largestArea) {
            minPerimeter = Math.min(minPerimeter, perimeter);
        }
    }

    static boolean OOB(int x,int y){
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
