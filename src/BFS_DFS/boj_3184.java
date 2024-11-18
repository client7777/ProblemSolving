package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_3184
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
                    //탐색 시작점은 방문하지 않은 좌표이면서 벽이 아닌 곳은 모두 가능
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
        //탐색 시작점이 양이거나 늑대인 경우 처리
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
                //다음 좌표가 양이거나 늑대인 경우
                if(map[nX][nY] == 'o') cntO++;
                else if(map[nX][nY] == 'v') cntV++;
            }
        }
        //같은 영역 안에 양이 늑대보다 많으면 양을 쫓아냄
        //아니면 잡아먹힘
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
