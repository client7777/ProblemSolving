package BFS_DFS;
//섬의 개수
import java.io.*;
import java.util.*;

public class boj_4963
{
    static int w,h;
    static int[][] map;
    static boolean[][] visit;
    static StringBuilder sb = new StringBuilder();
    //상, 하, 좌, 우, 대각을 나타낼 방향벡터
    static int[] dx = {-1,0,1, 0,-1,-1,1,1}; 
    static int[] dy = { 0,1,0,-1,1,-1,-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            //마지막 입력은 0 0
            if(w == 0 && h == 0 )
            {
                break;
            }
            map = new int[h][w];

            for(int i=0; i<h; i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++)
                {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            bfs();
        }
        //정답 출력
        System.out.print(sb);
    }
    static void bfs()
    {
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        visit = new boolean[h][w];
        for(int i=0; i<h; i++)
        {
            for(int j=0; j<w; j++)
            {
                if(map[i][j] == 1 && !visit[i][j])
                {
                    cnt++;
                    q.add(new int[]{i,j});
                    visit[i][j] = true;
                    while (!q.isEmpty())
                    {
                        int[] cur = q.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        for(int dir=0; dir<8; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];
                            if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;
                            q.add(new int[]{nX,nY});
                            visit[nX][nY] = true;
                        }
                    }
                }
            }
        }
        sb.append(cnt).append('\n');
    }
    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= h || y >= w;
    }
}
