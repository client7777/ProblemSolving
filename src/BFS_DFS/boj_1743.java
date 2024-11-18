package BFS_DFS;
//영역별 최대 넓이 구하기 문제
import java.io.*;
import java.util.*;

public class boj_1743
{
    static int n,m,k;
    static int[][] map;
    static boolean[][] visit;
    static int maxArea = 0; // 최대 넓이
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        visit = new boolean[n+1][m+1];

        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1; // 쓰레기가 있는 구역은 1로 마킹
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=m; j++)
            {
                if(map[i][j] == 1 && !visit[i][j])
                {
                    visit[i][j] = true;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j});
                    int area = 1;
                    while (!q.isEmpty())
                    {
                        int[] cur = q.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        for(int dir=0; dir<4; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];
                            if(OOB(nX,nY) || map[nX][nY] == 0 || visit[nX][nY]) continue;
                            q.add(new int[]{nX, nY});
                            visit[nX][nY] = true;
                            area++;
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        System.out.print(maxArea);
    }
    
    //0인덱스, 1인덱스일 때 상황에 따라 범위를 확실하게 주자
    static boolean OOB(int x,int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
