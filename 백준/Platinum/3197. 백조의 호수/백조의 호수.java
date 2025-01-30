import java.io.*;
import java.util.*;

public class Main
{
    static int r,c;
    static char[][] map;
    static ArrayList<int[]> swan = new ArrayList<>();
    static Queue<int[]> swanQ = new LinkedList<>();
    static Queue<int[]> waterQ = new LinkedList<>();
    static boolean[][] visit_swan;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visit_swan = new boolean[r][c];
        for(int i=0; i<r; i++)
        {
            String str = br.readLine();
            for(int j=0; j<c; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'L')
                {
                    swan.add(new int[]{i,j});
                    map[i][j] = '.';
                }
                if(map[i][j] == '.')
                {
                    waterQ.add(new int[]{i,j});
                }
            }
        }
        swanQ.add(swan.get(0));
        visit_swan[swan.get(0)[0]][swan.get(0)[1]] = true;

        int day = 0;
        while (true)
        {
            if(moveSwan()) break;
            meltIce();
            day++;
        }
        System.out.print(day);
    }
    static void meltIce()
    {
        int size = waterQ.size();
        for(int i=0; i<size; i++)
        {
            int[] cur = waterQ.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY)) continue;
                if(map[nX][nY] == 'X')
                {
                    map[nX][nY] = '.';
                    waterQ.add(new int[]{nX,nY});
                }
            }
        }
    }
    static boolean moveSwan()
    {
        Queue<int[]> nextQ = new LinkedList<>();
        while (!swanQ.isEmpty())
        {
            int[] cur = swanQ.poll();
            int curX = cur[0];
            int curY = cur[1];
            if(curX == swan.get(1)[0] && curY == swan.get(1)[1]) return true;
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit_swan[nX][nY]) continue;
                visit_swan[nX][nY] = true;
                if(map[nX][nY] == '.') swanQ.add(new int[]{nX,nY}); // 현재 탐색중인 좌표가 물이면 이동 가능
                else if(map[nX][nY] == 'X') nextQ.add(new int[]{nX,nY}); // 얼음이 녹으면 다음날 탐색하기 위함
            }
        }
        swanQ = nextQ;
       return false;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}
