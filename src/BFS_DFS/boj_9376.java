package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_9376
{
    static int h,w;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] map;
    static boolean[][] visit;
    static ArrayList<int[]> prison;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h+2][w+2];
            for(int i=0; i<h+2; i++)
            {
                Arrays.fill(map[i], '.');
            }

            prison = new ArrayList<>();
            for(int i=1; i<=h; i++)
            {
                String str = br.readLine();
                for(int j=1; j<=w; j++)
                {
                    map[i][j] = str.charAt(j-1);
                    if(map[i][j] == '$')
                    {
                        prison.add(new int[]{i,j});
                    }
                }
            }
            int[][] sang = bfs(0,0);
            int[][] prison1 = bfs(prison.get(0)[0], prison.get(0)[1]);
            int[][] prison2 = bfs(prison.get(1)[0], prison.get(1)[1]);
            calDoor(sang, prison1, prison2);
        }
        System.out.print(sb);
    }
    static int[][] bfs(int startX, int startY)
    {
        int[][] cost = new int[h+2][w+2];
        for(int[] row:cost)
        {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[startX][startY] = 0;

        visit = new boolean[h+2][w+2];
        visit[startX][startY] = true;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{startX, startY, 0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '*') continue;
                pq.add(new int[]{nX,nY, map[nX][nY] == '#' ? curCnt + 1 : curCnt});
                cost[nX][nY] = map[nX][nY] == '#' ? curCnt + 1 : curCnt;
                visit[nX][nY] = true;
            }
        }
        return cost;
    }

    static void calDoor(int[][] d1, int[][] d2, int[][] d3)
    {
        int min = Integer.MAX_VALUE;

        for(int i=1; i<=h; i++)
        {
            for(int j=1; j<=w; j++)
            {
                if(map[i][j] == '*') continue;
                int tot = d1[i][j] + d2[i][j] + d3[i][j];
                if(map[i][j] == '#') tot -= 2; // 문은 한명만 열면 되므로 2를 빼줌
                min = Math.min(min, tot);
            }
        }
        sb.append(min).append('\n');
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > h+1 || y > w+1;
    }
}
/*
    static int h,w;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] map;
    static ArrayList<int[]> prison;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h+2][w+2];
            for(int i=0; i<h+2; i++)
            {
                Arrays.fill(map[i], '.');
            }

            prison = new ArrayList<>();
            for(int i=1; i<=h; i++)
            {
                String str = br.readLine();
                for(int j=1; j<=w; j++)
                {
                    map[i][j] = str.charAt(j-1);
                    if(map[i][j] == '$')
                    {
                        prison.add(new int[]{i,j});
                    }
                }
            }
            int[][] sang = bfs(0,0);
            int[][] prison1 = bfs(prison.get(0)[0], prison.get(0)[1]);
            int[][] prison2 = bfs(prison.get(1)[0], prison.get(1)[1]);
            calDoor(sang, prison1, prison2);
        }
        System.out.print(sb);
    }
    static int[][] bfs(int startX, int startY)
    {
        int[][] cost = new int[h+2][w+2];
        for(int[] row:cost)
        {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[startX][startY] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];

            if(curCnt > cost[curX][curY]) continue;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == '*') continue;

                int nextDist = curCnt + (map[nX][nY] == '#' ? 1 : 0);

                if(cost[nX][nY] > nextDist)
                {
                    cost[nX][nY] = nextDist;
                    q.add(new int[]{nX,nY,nextDist});
                }
            }
        }
        return cost;
    }

    static void calDoor(int[][] d1, int[][] d2, int[][] d3)
    {
        int min = Integer.MAX_VALUE;

        for(int i=1; i<=h; i++)
        {
            for(int j=1; j<=w; j++)
            {
                if(map[i][j] == '*') continue;
                int tot = d1[i][j] + d2[i][j] + d3[i][j];
                if(map[i][j] == '#') tot -= 2;
                min = Math.min(min, tot);
            }
        }
        sb.append(min).append('\n');
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > h+1 || y > w+1;
    }
*/