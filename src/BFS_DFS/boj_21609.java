package BFS_DFS;
//배열의 회전, 리스트 정렬, 중력
import java.io.*;
import java.util.*;

public class boj_21609
{
    static int n,m,ans;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<int[]> blockGroup = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true)
        {
            //방문여부 초기화
            visit = new boolean[n][n];
            // 그룹을 찾기전에 리스트를 비움. 이전 그룹에 대한 정보가 남아있을 수 있음
            blockGroup.clear(); 
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    if(map[i][j] > 0 && !visit[i][j])
                    {
                        findBlock(i,j);
                    }
                }
            }
            //그룹이 존재하지 않는다면
            if(blockGroup.isEmpty()) break;
            //해당 그룹의 블록을 제거, 점수 누적
            remove();
            //중력, 배열 회전
            gravity();
            rotate();
            gravity();
        }
        System.out.print(ans);
    }
    static void findBlock(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visit[x][y] = true;
        int size = 1;
        int rainbow = 0;
        int num = map[x][y];
        int row = x;
        int cal = y;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                if(map[nX][nY] == 0 || map[nX][nY] == num)
                {
                    if(map[nX][nY] == 0 ) rainbow++;
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                    size++;
                }
            }
        }
        if(size >= 2) blockGroup.add(new int[]{size, rainbow, row, cal});
        //무지개 블록은 몇개가 들어있는 상관없으므로 방문여부 초기화
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 0)
                {
                    visit[i][j] = false;
                }
            }
        }
        //그룹을 정렬
        blockGroup.sort((a,b) ->
        {
            //내림차순
            // 그룹의 크기
            if(a[0] != b[0])
                return b[0] - a[0];
            //무지개 블록의 개수
            if(a[1] != b[1])
                return b[1] - a[1];
            //행
            if(a[2] != b[2])
                return b[2] - a[2];
            //열
            return b[3] - a[3];
        });
    }
    static void remove()
    {
        int x = blockGroup.get(0)[2];
        int y = blockGroup.get(0)[3];
        int num = map[x][y];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        map[x][y] = -2;
        int cnt = 1;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || map[nX][nY] == -1 || map[nX][nY] == -2) continue;
                if(map[nX][nY] == 0 || map[nX][nY] == num)
                {
                    map[nX][nY] = -2;
                    q.add(new int[]{nX,nY});
                    cnt++;
                }

            }
        }
        ans += cnt * cnt;
    }
    static void gravity()
    {
        for (int col = 0; col < n; col++)
        {
            for (int row = n - 1; row >= 0; row--)
            {
                if (map[row][col] == -2) // 빈 공간일 경우
                {
                    int k = row - 1; // 빈 공간 위의 값을 찾을 때까지 올라감
                    while (k >= 0 && map[k][col] == -2) k--;

                    // 빈 공간 위에 블록이 있다면 내림
                    if (k >= 0 && map[k][col] != -1)
                    {
                        map[row][col] = map[k][col]; // 블록을 아래로 내림
                        map[k][col] = -2; // 블록이 있던 위치는 빈 공간으로 변경
                    }
                }
            }
        }
    }
    static void rotate()
    {
        int[][] tmp = new int[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                tmp[n-j-1][i] = map[i][j];
            }
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                map[i][j] = tmp[i][j];
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
