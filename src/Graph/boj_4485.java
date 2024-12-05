package Graph;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485
{
    static int n;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int test_case = 1;
        while (true)
        {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            map = new int[n][n];
            dist = new int[n][n];

            for(int i=0; i<n; i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++)
                {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int min = dijkstra();
            sb.append("Problem " + test_case + ": " + min).append('\n');
            test_case++;
        }
        System.out.print(sb);
    }
    static int dijkstra()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{0,0,map[0][0]});
        for(int i=0; i<n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = map[0][0];

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];

            if(curX == n-1 && curY == n-1) break;

            if(curCost > dist[curX][curY]) continue;

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
        return dist[n-1][n-1];
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
