import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static int[][] init, after;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        init = new int[n][m];
        after = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                init[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean escape = false;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(init[i][j] != after[i][j])
                {
                    bfs(i,j);
                    escape = true;
                    break;
                }
            }

            if(escape) break;
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(init[i][j] != after[i][j])
                {
                    System.out.print("NO");
                    return;
                }
            }
        }

        System.out.print("YES");
    }

    static void bfs(int x,int y)
    {
        int initNum = init[x][y];
        int afterNum = after[x][y];

        init[x][y] = afterNum;

        boolean[][] visit = new boolean[n][m];
        visit[x][y] = true;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{x,y});

        while (!dq.isEmpty())
        {
            int[] cur = dq.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || init[nX][nY] != initNum) continue;
                visit[nX][nY] = true;
                dq.add(new int[]{nX,nY});
                init[nX][nY] = afterNum;
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
