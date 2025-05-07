import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int w;
    static int h;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0){
                break;
            }

            map = new char[h][w];

            int startX = -1;
            int startY = -1;

            for(int i=0; i<h; i++){
                String row = br.readLine();
                for(int j=0; j<w; j++)
                {
                    map[i][j] = row.charAt(j);

                    if(map[i][j] == '@'){
                        startX = i;
                        startY = j;
                    }
                }
            }

            sb.append(bfs(startX, startY)).append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(int x,int y) {
        boolean[][] visit = new boolean[h][w];
        visit[x][y] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));

        int cnt = 1;
        while (!q.isEmpty()){
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int dir=0; dir<4; dir++){
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == '#' || visit[nX][nY]) continue;
                cnt++;
                visit[nX][nY] = true;
                q.add(new Node(nX,nY));
            }
        }

        return cnt;
    }

    static boolean OOB(int x,int y){
        return x < 0 || y < 0 || x >= h || y >= w;
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
