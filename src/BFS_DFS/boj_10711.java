package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_10711
{
    static int n,m;
    static char[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {-1,0,1,0,-1,-1,1,1};
    static int[] dy = {0,1,0,-1,-1,1,-1,1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '.') q.add(new int[]{i,j});
            }
        }

        bfs();
    }

    static void bfs()
    {
        int day = 0;

        while (!q.isEmpty())
        {
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];

                for(int dir=0; dir<8; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];

                    if(OOB(nX,nY)) continue;

                    if(map[nX][nY] != '.')
                    {
                        // 자바에서 char는 내부적으로 숫자로 저장되기 때문에 -- 연산이 가능
                        // '3' - 1 = '2' '1' - 1 = '0'
                        map[nX][nY]--;

                        if(map[nX][nY] == '0')
                        {
                            map[nX][nY] = '.';
                            q.add(new int[]{nX,nY});
                        }
                    }

                }
            }
            day++;
        }

        System.out.print(--day);
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
