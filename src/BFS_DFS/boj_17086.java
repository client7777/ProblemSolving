package BFS_DFS;
//계산할 대상이 상어가 아니라 빈칸임
import java.io.*;
import java.util.*;

public class boj_17086
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0,-1,-1,1,1};
    static int[] dy = {0,1,0,-1,-1,1,-1,1};
    static ArrayList<int[]> shark = new ArrayList<>(); // 상어의 좌표를 저장
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
                if(map[i][j] == 1)
                {
                    shark.add(new int[]{i,j});
                }
            }
        }
        System.out.print(bfs());
    }
    static int bfs()
    {
        int[][] dist = new int[n][m];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(dist[i], -1);
        }
        for(int[] start:shark)
        {
            dist[start[0]][start[1]] = 0;
        }
        Queue<int[]> q = new LinkedList<>();
        for(int[] start:shark)
        {
            q.add(start);
        }
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<8; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || dist[nX][nY] != -1) continue;
                dist[nX][nY] = dist[curX][curY] + 1;
                q.add(new int[]{nX,nY});
            }
        }
        int max = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 0)
                {
                    max = Math.max(max, dist[i][j]);
                }
            }
        }
        return max;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
