package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_23288
{
    static int n,m,k;
    static int ans = 0;
    static int startX = 1, startY = 1, startDir = 0;
    static int[] dx = {0,1,0,-1}; // 동 -> 남 -> 서 -> 북
    static int[] dy = {1,0,-1,0};
    static int[][] map;
    static int[] dice = {1,2,3,4,5,6};
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<k; i++)
        {
            moveDice();
        }
        System.out.print(ans);
    }
    static void moveDice()
    {
        int nX = startX + dx[startDir];
        int nY = startY + dy[startDir];
        if(OOB(nX,nY))
        {
            startDir = (startDir+2) % 4;
            nX = startX + dx[startDir];
            nY = startY + dy[startDir];
        }

        rollDice(startDir);

        bfs(nX,nY);

        int bottom = dice[5];
        int value = map[nX][nY];
        if(bottom > value)
        {
            startDir = (startDir + 1) % 4; //시계 방향 회전
        }
        //else로 조건을 처리하게 되면 bottom 값과 value 값이 같은 경우도 포함해서 정확한 방향을 구하지 못함
        else if(value > bottom)
        {
            startDir = (startDir + 3) % 4; //반시계 방향 회전
        }
        startX = nX;
        startY = nY;

    }
    //값을 바꿔줄 때 순서 중요.
    static void rollDice(int dir)
    {
        int temp = dice[5];
        switch (dir)
        {
            //동
            case 0:
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = temp;
                break;
            //남    
            case 1:
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = temp;
                break;
            //서    
            case 2:
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = temp;
                break;
            //북
            case 3:
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = temp;
                break;
        }
    }
    static void bfs(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        boolean[][] visit = new boolean[n+1][m+1];
        visit[x][y] = true;
        int cnt = 1;
        int score = map[x][y];
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] != score) continue;
                cnt++;
                visit[nX][nY] = true;
                q.add(new int[]{nX,nY});
            }
        }
        ans += score * cnt;
    }
    static boolean OOB(int x, int y)
    {
        return x < 1 || y < 1 || x > n || y > m;
    }
}
