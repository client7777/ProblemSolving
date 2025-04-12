import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 0 && !visit[i][j])
                {
                    cnt++;
                    bfs(i,j);
                }
            }
        }

        System.out.print(cnt);
    }

    static void bfs(int x,int y)
    {
        visit[x][y] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x,y));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int dir=0; dir<4; dir++)
            {
                int nX = (curX + dx[dir] + n) % n;
                int nY = (curY + dy[dir] + m) % m;

                if(map[nX][nY] == 1 || visit[nX][nY]) continue;

                visit[nX][nY] = true;
                q.add(new Node(nX,nY));
            }
        }
    }

    static class Node
    {
        int x;
        int y;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
