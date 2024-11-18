package BFS_DFS;
//연구소
import java.io.*;
import java.util.*;

public class boj_14502
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int safeArea = Integer.MIN_VALUE;
    static ArrayList<int[]> wall = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                {
                    //빈칸이라면 벽을 세울 수 있는 칸이므로 리스트에 좌표를 추가
                    wall.add(new int[]{i,j});
                }
            }
        }
        funcwall(0,0);
        System.out.print(safeArea);

    }
    //벽을 세운다. -> 바이러스를 뿌린다. -> 안전영역의 개수를 구한다.
    //벽을 세우는 함수
    static void funcwall(int w,int at)
    {
        if(w == 3)
        {
            // 벽을 모두 세운 경우 바이러스를 뿌리러 감
            virus();
            return;
        }
        // 모든 빈칸에 대해서 벽을 세울 수 있으므로 모든 빈칸의 좌표중에 3개를 중복을 허용하지 않고 뽑아서
        // 안전영역이 최대가 되는 경우를 구한다.
        for(int i=at; i<wall.size(); i++)
        {
            int[] cur = wall.get(i);
            int x = cur[0];
            int y = cur[1];
            //벽을 세우고, 재귀호출, 백트래킹
            map[x][y] = 1;
            funcwall(w + 1, i + 1);
            map[x][y] = 0;
        }
    }
    static void virus()
    {
        Queue<int[]> q = new LinkedList<>();
        int[][] copyMap = new int[n][m];
        //배열을 복사
        for(int i=0; i<n; i++)
        {
            System.arraycopy(map[i],0,copyMap[i],0,m);
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 2)
                {
                    q.add(new int[]{i,j});
                }
            }
        }
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || copyMap[nX][nY] != 0) continue;
                q.add(new int[]{nX,nY});
                copyMap[nX][nY] = 2;
            }
        }
        //바이러스를 모두 뿌렸으면 안전영역의 개수를 구하러 감
        //복사한 배열을 매개변수로 넘겨서 안정영역의 개수를 구함
        countArea(copyMap);
    }
    static void countArea(int[][] copyMap)
    {
        int safe = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(copyMap[i][j] == 0) safe++;
            }
        }
        safeArea = Math.max(safeArea, safe);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
