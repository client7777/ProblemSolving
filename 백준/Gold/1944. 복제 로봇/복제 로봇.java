import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] parent;
    static char[][] map;
    static int[][] state;
    static int startX, startY;
    static int nodeNum = 1;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 미로의 크기
        m = Integer.parseInt(st.nextToken()); // 열쇠 개수

        state = new int[n][n];
        map = new char[n][n];
        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                    state[i][j] = nodeNum++;
                }
                if(map[i][j] == 'K') {
                    state[i][j] = nodeNum++;
                }
            }
        }
        parent = new int[nodeNum]; // 부모 배열 크기 수정
        for(int i=1; i<nodeNum; i++) {
            parent[i] = i;
        }

        // 모든 키 및 시작점에서 BFS 수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'S' || map[i][j] == 'K') {
                    bfs(i, j); // 각 출발점에서 BFS 수행
                }
            }
        }

        kruskal(); // 크루스칼 수행
    }

    static void kruskal() {
        int tot = 0;
        int used = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int dist = cur[2];
            if(find(from) != find(to)) {
                tot += dist;
                used++;
                union(from, to);
            }
        }
        System.out.print(used == m ? tot : -1);
    }

    static void union(int x,int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    static int find(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    // BFS 수정: 모든 키 또는 시작점에서 출발 가능
    static void bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0, state[startX][startY]});
        boolean[][] visit = new boolean[n][n];
        visit[startX][startY] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            int fromNode = cur[3];
            for(int dir=0; dir<4; dir++) {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || map[nX][nY] == '1' || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY,curDist+1,fromNode});
                visit[nX][nY] = true;
                if(map[nX][nY] == 'K' || map[nX][nY] == 'S') {
                    pq.add(new int[]{fromNode, state[nX][nY], curDist + 1});
                }
            }
        }
    }

    static boolean OOB(int x,int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
