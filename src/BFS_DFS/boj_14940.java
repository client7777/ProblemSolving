package BFS_DFS;

import java.io.*;
import java.util.*;
//원래 갈 수 있는 땅인 부분 중에 도달할 수 없는 곳은 -1 출력
//정답 배열을 -1로 초기화해서 탐색
public class boj_14940
{
    static int n,m;
    static int[][] map;
    static int[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int startX, startY = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new int[n][m];

        for(int i=0; i<n; i++)
        {
            Arrays.fill(visit[i],-1); // -1로 초기화해서 방문하지 않은 곳 표시
        }
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                {
                    startX = i;
                    startY = j;
                }
                if(map[i][j] == 0)
                {
                    visit[i][j] = 0; // 벽인 곳은 0으로 설정
                }
            }
        }
        bfs();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                sb.append(visit[i][j] + " ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        visit[startX][startY] = 0;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                //좌표가 범위 안에 있고, 벽이 아니고, 방문하지 않은 경우에만 탐색 진행
                if(OOB(nX,nY) || map[nX][nY] == 0 || visit[nX][nY] != -1) continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = visit[curX][curY] + 1;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y<0 || x >= n || y >= m;
    }
}
