package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17391
{
    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][m];

        System.out.print(bfs());
    }

    static int bfs()
    {
        visit[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,0,map[0][0]));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curCnt = cur.cnt;
            int curBooster = cur.booster;

            if(curX == n-1 && curY == m-1) return curCnt;

            for(int dir=0; dir<2; dir++)
            {
                for(int i=curBooster; i >= 1; i--)
                {
                    int nX = curX + dx[dir] * i;
                    int nY = curY + dy[dir] * i;

                    if(OOB(nX,nY) || visit[nX][nY]) continue;

                    visit[nX][nY] = true;
                    q.add(new Node(nX,nY,curCnt + 1, map[nX][nY]));
                }
            }
        }

        return -1;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }

    static class Node
    {
        int x;
        int y;
        int cnt;
        int booster;

        public Node(int x, int y, int cnt, int booster)
        {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.booster = booster;
        }
    }
}
