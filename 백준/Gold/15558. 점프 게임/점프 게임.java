import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,k;
    static int[][] map;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2][n];

        for(int i=0; i<2; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
    }

    static void bfs()
    {
        boolean[][] visit = new boolean[2][n];
        visit[0][0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0}); // 행, 열, 시간

        int[] d = {-1,1,k};

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curTime = cur[2];

            for(int i=0; i<3; i++)
            {
                int nX = curX;
                int nY = curY + d[i];

                if(i == 2)
                {
                    nX = (curX == 1) ? 0 : 1;
                }

                if(nY >= n)
                {
                    System.out.print(1);
                    return;
                }

                if(nY <= curTime || visit[nX][nY] || map[nX][nY] == 0) continue;
                q.add(new int[]{nX,nY,curTime + 1});
                visit[nX][nY] = true;
            }
        }
        System.out.print(0);
    }
}
