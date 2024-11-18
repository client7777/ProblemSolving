package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_20058
{
    static int n,q;
    static int[] level;
    static int row,cal;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visit;
    static int sum , max = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        //시전할 단계를 배열에 저장
        level = new int[q];
        //행과 열의 크기 = 2 ^ n
        row = cal = 1 << n;
        map = new int[row][cal];
        visit = new boolean[row][cal];
        for(int i=0; i<row; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<cal; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken() );
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++)
        {
            level[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<q; i++)
        {
            divideGrid(level[i]);
            meltIce();
        }
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<cal; j++)
            {
                sum += map[i][j];
                if(map[i][j] != 0 && !visit[i][j])
                {
                    bfs(i,j);
                }
            }
        }
        sb.append(sum).append('\n').append(max);
        System.out.print(sb);
    }
    static void divideGrid(int level)
    {
        //2^l * 2^l 크기의 부분 격자로 나눔
        int size = 1 << level;
        for(int i=0; i<row; i += size)
        {
            for(int j=0; j<cal; j += size)
            {
                rotate(i,j,size);
            }
        }
    }
    static void rotate(int startX, int startY, int size)
    {
        //회전시킨 배열을 저장할 임시 배열
        int[][] tmp = new int[size][size];
        for(int r = 0; r<size; r++)
        {
            for(int c = 0; c<size; c++)
            {
                tmp[c][size - 1 -r] = map[startX + r][startY + c];
            }
        }
        //원본배열에 회전한 배열을 대입
        for(int r = 0; r<size; r++)
        {
            for(int c = 0; c<size; c++)
            {
                map[startX + r][startY + c] = tmp[r][c];
            }
        }
    }
    static void meltIce()
    {
        //상태를 즉시 변경하면, 녹여야 할 다른 칸이 이미 변경된 상태의 영향을 받을 수 있다.
        //모든 칸에 대해 동시에 녹는 작업이 이루어져야 함. 어떤 칸이 먼저 녹아도 다른 칸들의 계산에 영향을 주지 않아야 함
        //원본 배열의 값에 의존하여 처리해야 하는 문제에서 자주 보이는 패턴
        int[][] tmp =new int[row][cal];
        for(int i=0; i<row; i++)
        {
            tmp[i] = Arrays.copyOf(map[i],cal);
        }
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<cal; j++)
            {
                if(map[i][j] != 0)
                {
                    int cnt = 0;
                    for(int dir=0; dir<4; dir++)
                    {
                        int nX = i + dx[dir];
                        int nY = j + dy[dir];
                        if(!OOB(nX,nY) && map[nX][nY] > 0) cnt++;
                    }
                    if(cnt < 3) tmp[i][j] --;
                }
            }
        }
        map = tmp;
    }
    static void bfs(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visit[x][y] = true;
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
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;
                area ++;
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
        }
        //최대 영역의 넓이를 구함
        max = Math.max(max, area);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y <  0 || x >= row || y >= cal;
    }
}
