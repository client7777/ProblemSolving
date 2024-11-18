package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_17141
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<int[]> virus = new ArrayList<>();
    static ArrayList<int[]> comb  = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
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
        backTrack(0,0);
        System.out.print(ans == Integer.MAX_VALUE? - 1 : ans);
    }
    static void backTrack(int depth, int at)
    {
        if(depth == m)
        {
            bfs(comb);
            return;
        }
        for(int i=at; i<virus.size(); i++)
        {
            comb.add(virus.get(i));
            backTrack(depth + 1, i+1);
            comb.remove(comb.size() - 1);
        }
    }
    static void bfs(ArrayList<int[]> start)
    {
        boolean[][] visit = new boolean[n][n];
        int[][] time = new int[n][n];
        for(int i=0; i<n; i++)
        {
            Arrays.fill(time[i],-1);
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 1)
                {
                    //원본 맵에서 벽이 있는 자리는 시간 배열에서 7로 따로 표시
                    time[i][j] = 7;
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for(int[] next:start)
        {
            q.add(next);
            visit[next[0]][next[1]] = true;
            time[next[0]][next[1]] = 0;
        }
        int max = 0;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                //이미 방문했거나 범위 밖이거나 이미 시간을 표시한 좌표라면 무시
                if(OOB(nX,nY) || visit[nX][nY] || time[nX][nY] != -1) continue;
                visit[nX][nY] = true;
                time[nX][nY] = time[curX][curY] + 1;
                q.add(new int[]{nX,nY});
                max = Math.max(max, time[nX][nY]);
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                //시간을 표시하지 못한 곳, 방문하지 않은 빈칸이 있다면 최소시간을 갱신하지 않고 함수 종료
                if(time[i][j] == -1) return;
            }
        }
        ans = Math.min(ans, max);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
