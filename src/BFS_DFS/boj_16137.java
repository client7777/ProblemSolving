package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_16137
{
    static int n,t;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //절벽이 교차해서 오작교를 놓지 못하는 곳 미리 체크
        findCross();
        System.out.print(bfs());
    }

    static int bfs()
    {
        // 0 -> 오작교를 짓지 않음 1 -> 오작교를 지음
        boolean[][][] visit = new boolean[n][n][2];
        visit[0][0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,0,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curTime = cur.time;
            int curState = cur.state;

            if(curX == n-1 && curY == n-1) return curTime;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY][curState] || map[nX][nY] == -1) continue;

                if(map[nX][nY] == 1)
                {
                    visit[nX][nY][curState] = true;
                    q.add(new Node(nX,nY,curTime + 1, curState));
                }
                else if(map[nX][nY] >= 2 && map[curX][curY] == 1)
                {
                    if((curTime + 1) % map[nX][nY] == 0)
                    {
                        visit[nX][nY][curState] = true;
                        q.add(new Node(nX,nY,curTime + 1, curState));
                    }
                    else
                        q.add(new Node(curX, curY, curTime + 1, curState));
                }
                else if(map[nX][nY] == 0 && curState == 0 && map[curX][curY] == 1)
                {
                    if((curTime + 1) % t == 0)
                    {
                        visit[nX][nY][1] = true;
                        q.add(new Node(nX,nY,curTime + 1, 1));
                    }
                    else
                        q.add(new Node(curX, curY, curTime + 1, curState));
                }
            }
        }

        return 0;
    }

    static void findCross()
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 0)
                {
                    int cnt = 0;

                    for(int dir=0; dir<4; dir++)
                    {
                        int nX = i + dx[dir];
                        int nY = j + dy[dir];

                        if(OOB(nX,nY)) continue;

                        if(map[nX][nY] == 0) cnt++;
                    }

                    if(cnt >= 2) map[i][j] = -1;
                }
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }

    static class Node
    {
        int x;
        int y;
        int time;
        int state;

        public Node(int x, int y, int time, int state)
        {
            this.x = x;
            this.y = y;
            this.time = time;
            this.state = state;
        }
    }
}