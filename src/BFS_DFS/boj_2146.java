package BFS_DFS;
//다리 만들기
//각 육지를 마킹해서 번호를 붙여줌
//해안선의 좌표를 큐에 추가해서 다른 섬까지의 최단 거리를 탐색함
//시간복잡도 = 섬에 번호를 붙이는 과정 = O(n*n) 최단 거리를 찾는 과정 = O(k + n*n) k = 섬의 개수
//섬에 번호를 붙이는 과정과 최단 거리를 찾는 과정 모두 최악의 경우 맵 전체를 탐색할 수 있다.
//k는 일반적으로 n*n보다 훨씬 작으므로 시간복잡도는 O(n^2)에 비례한다.
import java.io.*;
import java.util.*;
public class boj_2146
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
            int curX = cur[0]; // x좌표
            int curY = cur[1]; // y좌표
            int curDist = cur[2]; // 현재 거리
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                //다음 방문할 좌표가 맵의 범위 안에 존재하고
                if(!OOB(nX,nY))
                {
                    // 방문하지 않은 바다이면
                    if(map[nX][nY] == 0 && !local[nX][nY])
                    {
                        //좌표를 큐에 추가하고 거리를 누적
                        q.add(new int[]{nX, nY, curDist+1});
                        local[nX][nY] = true;
                    }
                    //만약 다른 섬을 만난다면 현재 거리를 리턴
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
