package BFS_DFS;
// 문제 꼼꼼히 읽기, 문제에서 주는 정의 제대로 파악하기
// 산봉우리 = 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1245
{
    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static int cnt = 0;
    static int[] dx = {-1,0,1,0,-1,-1,1,1};
    static int[] dy = {0,1,0,-1,-1,1,-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(!visit[i][j] && map[i][j] > 0)
                {
                    if(bfs(i,j)) cnt++;
                }
            }
        }
        System.out.print(cnt);
    }
    static boolean bfs(int startX, int startY)
    {
        boolean isPeak = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        visit[startX][startY] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<8; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY)) continue;
                if(map[nX][nY] > map[curX][curY])
                {
                    isPeak = false;
                }
                if(!visit[nX][nY] && map[nX][nY] == map[curX][curY])
                {
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                }
            }
        }
        return isPeak;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
