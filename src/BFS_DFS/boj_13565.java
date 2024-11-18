package BFS_DFS;
//침투
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_13565 {
    static int m, n;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m + 1][n];
        visit = new boolean[m + 1][n];

        for (int i = 1; i <= m; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < n; j++)
            {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        visit[0][0] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            if (curX == m)
            {
                System.out.print("YES");
                return;
            }

            for (int dir = 0; dir < 4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if (OOB(nX, nY) || visit[nX][nY] || map[nX][nY] == 1) continue;
                q.add(new int[]{nX, nY});
                visit[nX][nY] = true;
            }
        }
        System.out.print("NO");
    }

    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x > m || y >= n;  // x는 m까지 허용
    }
}
