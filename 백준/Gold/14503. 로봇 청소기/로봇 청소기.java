import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int startX,startY,startDir;
    static int[][] map;
    static int[] dx = {-1,0,1,0}; // 북동남서
    static int[] dy = {0,1,0,-1};
    static int cnt = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startDir = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(startX, startY, startDir);
        System.out.print(cnt);
    }
    static void clean(int x,int y,int dir)
    {
        // 현재 위치를 청소
        if(map[x][y] == 0)
        {
            cnt++;
            map[x][y] = 2;
        }
        for(int i=0; i<4; i++)
        {
            dir = (dir+3) % 4;
            int nX = x + dx[dir];
            int nY = y + dy[dir];

            if(!OOB(nX,nY) && map[nX][nY] == 0)
            {
                clean(nX,nY,dir);
                return;
            }
        }
        int backDir = (dir + 2) % 4;
        int bx = x + dx[backDir];
        int by = y + dy[backDir];

        if(!OOB(bx,by) && map[bx][by] != 1)
        {
            clean(bx,by,dir); // 바라보는 방향을 유지한채 뒤로 한칸 이동
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
