import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static int answer = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.print(answer);
    }

    static void bfs()
    {
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0, 1, 1)); // 가로 상태 시작

        while (!q.isEmpty())
        {
            Position cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.state;

            if (x == n - 1 && y == n - 1)
            {
                answer++;
                continue;
            }

            // 가로 -> 가로
            if (dir == 1 || dir == 3)
            {
                if (y + 1 < n && map[x][y + 1] == 0)
                {
                    q.add(new Position(x, y + 1, 1));
                }
            }

            // 세로 -> 세로
            if (dir == 2 || dir == 3)
            {
                if (x + 1 < n && map[x + 1][y] == 0)
                {
                    q.add(new Position(x + 1, y, 2));
                }
            }

            // 대각선 이동 (모든 상태에서 가능)
            if (x + 1 < n && y + 1 < n &&
                    map[x][y + 1] == 0 &&
                    map[x + 1][y] == 0 &&
                    map[x + 1][y + 1] == 0)
            {
                q.add(new Position(x + 1, y + 1, 3));
            }
        }
    }

    static class Position
    {
        int x;
        int y;
        int state;

        public Position(int x, int y, int state)
        {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }
}
