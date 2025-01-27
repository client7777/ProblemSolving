import java.io.*;
import java.util.*;

public class Main
{
    static int n, m;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<int[]> pos = new ArrayList<>();
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    pos.add(new int[]{i, j}); // 바둑을 둘 수 있는 위치를 모두 저장
                }
            }
        }

        isUsed = new boolean[pos.size()];
        backTrack(0, 0);
        System.out.print(max);
    }

    static void backTrack(int start, int depth) {
        if (depth == 2) {
            boolean[][] visit = new boolean[n][m];
            int cnt = 0;

            // 모든 그룹 탐색
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 2 && !visit[i][j]) {  // 그룹의 돌만 탐색
                        cnt += bfs(visit, i, j);
                    }
                }
            }

            max = Math.max(max, cnt);
            return;
        }

        for (int i = start; i < pos.size(); i++) {
            if (!isUsed[i]) {
                int x = pos.get(i)[0];
                int y = pos.get(i)[1];
                map[x][y] = 1;  // 돌을 놓음
                isUsed[i] = true;
                backTrack(i + 1, depth + 1);  // 두 번째 돌 놓기
                map[x][y] = 0;  // 돌을 되돌림
                isUsed[i] = false;
            }
        }
    }

    static int bfs(boolean[][] visit, int x, int y) {
        visit[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        int size = 1; // 시작점을 포함한 그룹 크기
        boolean flag = false; // 포획된 그룹인지 확인

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if (OOB(nX, nY) || visit[nX][nY] || map[nX][nY] == 1) continue;  // 경계 밖이거나 방문했거나 벽인 경우

                if (map[nX][nY] == 0) {  // 빈 칸은 포획되지 않음
                    flag = true;
                }

                visit[nX][nY] = true;
                size++;
                q.add(new int[]{nX, nY});
            }
        }

        return flag ? 0 : size;  // 포획되지 않은 그룹만 크기 반환
    }

    static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
