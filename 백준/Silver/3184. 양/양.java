import java.io.*;
import java.util.*;

public class Main
{
    static int r,c;
    static int[][] map;
    static boolean[][] visit;
    static int totO, totV;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
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
        System.out.print(totO + " " + totV);
    }
    static void bfs(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        int cntO = 0;
        int cntV = 0;
        if(map[x][y] == 'o')
        {
            cntO++;
        }
        else if(map[x][y] == 'v')
        {
            cntV++;
        }
        visit[x][y] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '#') continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
                if(map[nX][nY] == 'o') cntO++;
                else if(map[nX][nY] == 'v') cntV++;
            }
        }
        if(cntO > cntV)
        {
            totO += cntO;
        }
        else
            totV += cntV;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y< 0 || x>=r || y>= c;
    }
}
