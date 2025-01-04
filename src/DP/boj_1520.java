package DP;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1520
{
    static int n,m;
    static int[][] map, d;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d = new int[n][m]; // d[i][j] = (i,j)까지 가능한 경로의 수
        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                d[i][j] = -1;
            }
        }
        System.out.print(dfs(0,0));
    }
    static int dfs(int x,int y)
    {
        if(x == n-1 && y == m-1)
        {
            return 1;
        }
        if(d[x][y] != -1) return d[x][y]; // 이미 계산이 끝난 경우 바로 반환

        d[x][y] = 0;
        for(int dir=0; dir<4; dir++)
        {
            int nX = x + dx[dir];
            int nY = y + dy[dir];

            if(OOB(nX,nY)) continue;

            if(map[x][y] > map[nX][nY])
                d[x][y] += dfs(nX,nY);
        }
        return d[x][y];
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
// d 배열을 -1로 초기화 하는 이유 : 아직 (x,y)에서 시작하는 경로의 수를 계산하지 않았음을 나타냄
// 만약 d[x][y]의 값이 -1이 아니라면 해당 좌표에서 시작하는 경로의 수가 계산이 됐다는 의미이고
// 이후 다시 해당 좌표를 탐색할 때 저장된 값을 사용
// 0을 초기값으로 사용했을 때 발생할 수 있는 문제점은 탐색이 끝났지만 경로가 없는 상태와 탐색을 하지 않은 상태를 구분하기 어려워짐
