package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_2636
{
    static int n,m;
    static int[][] map;
    static int time, cheese, lastCheese;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static StringBuilder sb = new StringBuilder();
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
                    cheese++;
                }
            }
        }
        time = 0;
        lastCheese = cheese;
        while (cheese > 0)
        {
            lastCheese = cheese; // bfs 호출 직전에 현재 치즈의 개수를 저장
            bfs();
            time++;
        }
        sb.append(time).append('\n').append(lastCheese);
        System.out.print(sb);
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];
        q.add(new int[]{0,0});
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
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                //치즈를 만나지 않았으면 탐색을 이어감
                if(map[nX][nY] == 0)
                {
                    visit[nX][nY] = true;
                    q.add(new int[]{nX,nY});
                }
                //테두리에 있는 치즈를 만났으면 녹이고, 치즈의 개수를 감소
                //테두리에 있는 치즈만 녹이므로 큐에 좌표를 추가하지 않음
                else
                {   
                    map[nX][nY] = 0;
                    visit[nX][nY] = true;
                    cheese--;
                }
            }
        }

    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
