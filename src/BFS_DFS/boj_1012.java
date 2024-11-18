package BFS_DFS;
// 유기농 배추
// 각 테스트 케이스에 대해서 맵 전체를 한 번 방문하기 때문에 시간복잡도 = O(n*m) + k
import java.io.*;
import java.util.*;

public class boj_1012
{
    static int test_case, n,m,k;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        test_case = Integer.parseInt(st.nextToken());

        for(int t=0;t<test_case;t++)
        {
            int warm = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            visit = new boolean[n][m];


            for(int i=0; i<k; i++)
            {
                //k번 데이터를 공백을 기준으로 한줄씩 나눠서 x,y좌표 저장
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1; // 지렁이가 있는 곳은 1로 마킹
            }
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<m; j++)
                {
                    //방문하지 않은 지렁이가 있는 곳이라면
                    if(map[i][j] == 1 && !visit[i][j])
                    {
                        //탐색
                        bfs(i,j);
                        warm++;
                    }
                }
            }
            sb.append(warm).append("\n");
        }
        System.out.println(sb);
    }
    static void bfs(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visit[x][y] = true;
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;
                visit[nX][nY] = true;
                q.add(new int[]{nX,nY});
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}
