import java.io.*;
import java.util.*;

public class Main
{
    static int n,q;
    static int[] level;
    static int row,cal;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visit;
    static int max, sum = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        level = new int[q];
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

        // Rotate and melt ice for each level
        for(int i=0; i<q; i++)
        {
            divideGrid(level[i]);
            meltIce();
        }

        // Calculate total ice and find largest ice cluster
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<cal; j++)
            {
                if(map[i][j] != 0 && !visit[i][j])
                {
                    bfs(i,j); // Sum ice in bfs
                }
            }
        }
        
        sb.append(sum).append('\n').append(max);
        System.out.print(sb);
    }

    static void divideGrid(int level)
    {
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
        int[][] tmp = new int[size][size];
        for(int r = 0; r<size; r++)
        {
            for(int c = 0; c<size; c++)
            {
                tmp[c][size - 1 - r] = map[startX + r][startY + c];
            }
        }
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
        int[][] tmp = new int[row][cal];
        for(int i=0; i<row; i++)
        {
            tmp[i] = Arrays.copyOf(map[i], cal);
        }

        for(int i=0; i<row; i++)
        {
            for(int j=0; j<cal; j++)
            {
                if(map[i][j] > 0)
                {
                    int cnt = 0;
                    for(int dir=0; dir<4; dir++)
                    {
                        int nX = i + dx[dir];
                        int nY = j + dy[dir];
                        if(!OOB(nX,nY) && map[nX][nY] > 0) cnt++;
                    }
                    if(cnt < 3) tmp[i][j]--;
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
        int localSum = map[x][y];  // Track sum of this cluster's ice

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
                
                area++;
                localSum += map[nX][nY];  // Add to local sum of ice
                q.add(new int[]{nX,nY});
                visit[nX][nY] = true;
            }
        }
        sum += localSum;  // Add cluster's ice sum to total sum
        max = Math.max(max, area);  // Track largest cluster
    }

    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= row || y >= cal;
    }
}
