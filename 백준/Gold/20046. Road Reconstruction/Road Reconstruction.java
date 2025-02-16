import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static final int INF = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
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
        
        //시작 노드에서 목적지 노드까지 최소한의 도로 건설 비용을 지불해서 도착
        dijkstra();
    }

    static void dijkstra()
    {
        if(map[0][0] == -1 || map[n-1][m-1] == -1)
        {
            System.out.print(-1);
            return;
        }
        int[][] dist = new int[n][m];
        for(int[] row:dist)
        {
            Arrays.fill(row, INF);
        }
        dist[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{0,0,map[0][0]}); // x,y,cost

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];

            if(curX == n-1 && curY == m-1)
            {
                System.out.print(curCost);
                return;
            }

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == -1) continue;

                int nextCost = curCost;

                switch (map[nX][nY])
                {
                    case 0:
                        break;
                    case 1:
                        nextCost += 1;
                        break;
                    case 2:
                        nextCost += 2;
                        break;
                }

                if(dist[nX][nY] > nextCost)
                {
                    dist[nX][nY] = nextCost;
                    pq.add(new int[]{nX,nY,nextCost});
                }
            }
        }
        System.out.print(-1);
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
