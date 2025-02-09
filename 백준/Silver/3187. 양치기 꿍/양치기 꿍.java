import java.io.*;
import java.util.*;

public class Main
{
    static int r,c;
    static int wolf = 0, ship = 0;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visit = new boolean[r][c];

        for(int i=0; i<r; i++)
        {
            String str = br.readLine();
            for(int j=0; j<c; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                if(map[i][j] != '#' && !visit[i][j])
                {
                    bfs(i,j);
                }
            }
        }
        System.out.print(ship + " " + wolf);
    }
    static void bfs(int x,int y)
    {
       Queue<int[]> q = new LinkedList<>();
       q.add(new int[]{x,y});
       visit[x][y] = true;
       int shipCnt = 0;
       int wolfCnt = 0;
       while (!q.isEmpty())
       {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            if(map[curX][curY] == 'v') wolfCnt++;
            else if(map[curX][curY] == 'k') shipCnt++;
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '#') continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
       }
       if(shipCnt > wolfCnt) ship += shipCnt;
       else wolf += wolfCnt;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}
