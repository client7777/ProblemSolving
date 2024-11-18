package BFS_DFS;
//활성 바이러스 M개를 비활성 바이러스가 있는 곳에 놓아서 활성상태로 만들어서 모든 빈칸에 바이러스를 뿌림
import java.io.*;
import java.util.*;

public class boj_17142
{
    static int n,m;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map;
    static ArrayList<int[]> virus = new ArrayList<>();
    static ArrayList<int[]> activeVirus = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                {
                    virus.add(new int[]{i,j});
                }
            }
        }
        comb(0,0);
        System.out.print(ans == Integer.MAX_VALUE?-1:ans);
    }
    static void comb(int depth, int at)
    {
        if(depth == m)
        {
            //m개의 좌표를 모두 골랐다면 bfs 호출
            bfs(activeVirus);
            return;
        }
        for(int i=at; i<virus.size(); i++)
        {
            activeVirus.add(virus.get(i));
            comb(depth + 1, i+1);
            activeVirus.remove(activeVirus.size()-1);
        }
    }
    static void bfs(List<int[]> start)
    {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];
        int[][] time = new int[n][n];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(time[i],-1);
        }
        for(int[] next:start)
        {
            q.add(next);
            visit[next[0]][next[1]] = true;
            time[next[0]][next[1]] = 0;
        }
        int maxTime = 0;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1) continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
                time[nX][nY] = time[curX][curY] + 1;
                //모든 빈칸에 바이러스를 뿌리는 시간을 측정하므로 빈칸에 방문할 때마다 시간 갱신
                if(map[nX][nY] == 0)
                {
                    maxTime = Math.max(maxTime, time[nX][nY]);
                }
            }
        }
        //방문하지 않은 빈칸이 존재할 경우 시간을 갱신할 필요가 없음.
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 0 && time[i][j] == -1) return;
            }
        }
        ans = Math.min(ans, maxTime);

    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y <0 || x >=n || y>=n;
    }
}
