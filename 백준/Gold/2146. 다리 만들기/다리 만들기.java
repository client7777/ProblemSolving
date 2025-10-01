//다리 만들기
//각 육지를 마킹해서 번호를 붙여줌
import java.io.*;
import java.util.*;
public class Main
{
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int n;
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            visit = new boolean[n][n];

            for(int i = 0; i < n; i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++)
                {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //각 육지별로 번호를 붙여줌
            int islandNum = 2;
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    if(map[i][j] == 1 && !visit[i][j])
                    {
                        mark(i,j,islandNum++);
                    }
                }
            }
            int minLength = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    if(map[i][j] >= 2)
                    {
                        minLength = Math.min(minLength,bfs(i,j));
                    }
                }
            }
            System.out.print(minLength);
    }
    static int bfs(int x,int y)
    {
        boolean[][] local = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        int start = map[x][y];
        //해안선인지 체크
        for(int dir=0; dir<4; dir++)
        {
            int nX = x + dx[dir];
            int nY = y + dy[dir];
            if(!OOB(nX,nY) && map[nX][nY] == 0)
            {
                q.add(new int[]{nX, nY, 1});
                local[nX][nY] = true;
            }
        }
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!OOB(nX,nY))
                {
                    if(map[nX][nY] == 0 && !local[nX][nY])
                    {
                        q.add(new int[]{nX, nY, curDist+1});
                        local[nX][nY] = true;
                    }
                    else if(map[nX][nY] > 1 && map[nX][nY] != start)
                    {
                        return curDist;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static void mark(int x,int y,int num)
    {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{x,y});
            visit[x][y] = true;
            map[x][y] = num;
            while(!q.isEmpty())
            {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];
                for(int dir=0; dir<4; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    if(OOB(nX,nY) || map[nX][nY] == 0 || visit[nX][nY]) continue;
                    q.add(new int[]{nX,nY});
                    map[nX][nY] = num;
                    visit[nX][nY] = true;
                }
            }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}
