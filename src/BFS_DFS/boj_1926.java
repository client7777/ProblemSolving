package BFS_DFS;
//그림
//시간복잡도 = O(n*m)  n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)
//1초 = 1억
import java.io.*;
import java.util.*;
public class boj_1926
{
    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static int cnt = 0, maxArea = 0;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            visit = new boolean[n][m];

            for(int i=0;i<n;i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<m;j++)
                {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0;i<n;i++)
            {
                for(int j=0;j<m;j++)
                {
                    // 방문하지 않은 그림조각을 발견했다면
                    if(map[i][j] == 1 && !visit[i][j])
                    {
                        bfs(i,j);
                        cnt++;
                        //그림의 개수 증가
                    }
                }
            }
            sb.append(cnt).append("\n").append(maxArea);
            System.out.print(sb);
    }
    //발견한 그림조각을 시작점으로 탐색을 시작하여 가장 넓은 그림의 면적을 구함
    static void bfs(int x,int y)
    {
        int area = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visit[x][y] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;
                q.offer(new int[]{nX,nY});
                visit[nX][nY] = true;
                area++;
            }
        }
        maxArea = Math.max(area,maxArea);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}