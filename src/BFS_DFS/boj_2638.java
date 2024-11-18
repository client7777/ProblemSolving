package BFS_DFS;
//치즈
import java.io.*;
import java.util.*;

public class boj_2638
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int cheese = 0;
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
                if(map[i][j] == 1) cheese++;
            }
        }
        int time = 0;
        while (true)
        {
            //루프를 돌 때마다 치즈의 개수 체크
            if(cheese == 0) break;
            bfs();
            time++;
        }
        System.out.print(time);
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        boolean[][] visit = new boolean[n][m];
        int[][] area = new int[n][m];
        visit[0][0] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                //이미 방문한 노드이거나, 범위 밖이라면 무시
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                //외부 공기와 만났다면 큐에 좌표를 추가하고 탐색
                if(map[nX][nY] == 0)
                {
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                }
                //치즈를 만났다면
                else
                {
                    //외부 공기와 닿은 횟수 증가
                    area[nX][nY]++;
                    //해당 좌표의 치즈가 외부 공기와 2번 닿았다면
                    if(area[nX][nY] == 2)
                    {
                        //해당 지점은 빈칸처리
                        map[nX][nY] = 0;
                        //방문처리
                        visit[nX][nY] = true;
                        //전체 치즈의 개수 한개 감소
                        cheese--;
                    }
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
