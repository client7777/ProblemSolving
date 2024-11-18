package BFS_DFS;
//양치기
import java.io.*;
import java.util.*;

public class boj_3187
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
                if(map[i][j] == 'k')
                {
                    ship++;
                }
                else if(map[i][j] == 'v') wolf++;
            }
        }
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                // 울타리를 제외한 모든 지점, 늑대와 양이 있는 좌표도 탐색 시작지점이 될 수 있다.
                if(map[i][j] != '#' && !visit[i][j])
                {
                    bfs(i,j);
                }
            }
        }
        System.out.print(ship + " " + wolf);
    }
    //현재 영역에서 늑대와 양이 몇마리가 있는지 탐색
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
       //양이 늑대보다 많은 경우를 제외하면 양은 늑대한테 잡아먹힌다.
       if(shipCnt > wolfCnt) wolf -= wolfCnt;
       else ship -= shipCnt;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}
