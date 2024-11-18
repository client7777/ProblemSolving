package BFS_DFS;
//불켜기
import java.io.*;
import java.util.*;

public class boj_11967
{
    static int n,m;
    static int[][] board;
    static boolean[][] visit;
    static ArrayList<int[]>[][] switchs;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[n+1][n+1];
        board = new int[n+1][n+1];
        switchs = new ArrayList[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                switchs[i][j] = new ArrayList();
            }
        }
        while(m > 0)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switchs[x][y].add(new int[]{a,b});
            m--;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,1});
        board[1][1] = 1;
        visit[1][1] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int[] room: switchs[curX][curY])
            {
                int a = room[0];
                int b = room[1];
                if(visit[a][b]) continue;
                // 이미 방문한 방과 연결되어 있다면, 탐색이 가능하므로
                // 해당 방을 방문처리, 큐에 좌표를 추가
                if(is_conn(room))
                {
                    // 큐에 (a,b)를 추가하기 때문에 탐색이 보장됨.
                    // 방문처리를 해도 탐색이 보장되기 때문에 해당 방에 스위치가 존재해도 불을 켜지 못하는 경우 방지
                    q.add(new int[]{a,b});
                    visit[a][b] = true;
                }
                board[a][b] = 1;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || board[nX][nY] == 0 || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
        }
        int ans = 0;
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                ans += board[i][j];
            }
        }
        System.out.print(ans);
    }
    // 새로 불이 켜진 방이 이미 방문한 방과 연결되어 있는지 확인하는 함수
    static boolean is_conn(int[] nxt)
    {
        for(int dir=0; dir<4; dir++)
        {
            int nX = nxt[0] + dx[dir];
            int nY = nxt[1] + dy[dir];
            if(OOB(nX,nY)) continue;
            if(visit[nX][nY]) return true;
        }
        return false;
    }
    static boolean OOB(int x, int y)
    {
        return x < 1 || y < 1 || x > n || y > n;
    }
}
