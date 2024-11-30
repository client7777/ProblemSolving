package BFS_DFS;
// 0-base 인덱스인지 1-base 인덱스인지 체크 확실히
import java.io.*;
import java.util.*;

public class boj_14497
{   
    static int n,m;
    static int[][] map, dist;
    static boolean[][] visit;
    static int startX, startY, endX, endY;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        dist = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
        {
            String str = br.readLine();
            for(int j=1; j<=m; j++)
            {
                map[i][j] = str.charAt(j-1);
                if(map[i][j] == '1' || map[i][j] == '0')
                {
                    map[i][j] = map[i][j] - '0';
                }
            }
        }
        map[endX][endY] = 1;

        dijkstra();
    }
    static void dijkstra()
    {
        for(int i=1; i<=n; i++)
        {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        dist[startX][startY] = 0;
        pq.add(new int[]{startX, startY, dist[startX][startY]});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            if(curX == endX && curY == endY)
            {
                System.out.print(curDist);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY)) continue;

                if(dist[nX][nY] > dist[curX][curY] + map[nX][nY])
                {
                    dist[nX][nY] = dist[curX][curY] + map[nX][nY];
                    pq.add(new int[]{nX,nY,dist[nX][nY]});
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
/*
static void dijkstra()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        boolean[][] visit = new boolean[n+1][m+1];

        pq.add(new int[]{startX, startY, 0});
        visit[startX][startY] = true;

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];

            if(curX == endX && curY == endY)
            {
                System.out.print(curCost);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                visit[nX][nY] = true;
                pq.add(new int[]{nX,nY, map[nX][nY] == '0' ? curCost : curCost+1});
            }
        }
    }
*/

/*
//0-1 BFS
static void bfs()
    {
        visit[startX][startY] = true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startX, startY, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];
            if(curX == endX && curY == endY)
            {
                System.out.print(curCost);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                visit[nX][nY] = true;
                if(map[nX][nY] == '0')
                    q.addFirst(new int[]{nX,nY,curCost});
                else
                    q.addLast(new int[]{nX,nY,curCost+1});
            }
        }
    }
*/