import java.io.*;
import java.util.*;

public class Main
{
    static int r,c,n;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for(int i=0; i<r; i++)
        {
            String str = br.readLine();
            for(int j=0; j<c; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int h = Integer.parseInt(st.nextToken());
            brokeMieneral(r-h, i);
            bfs();
        }
        print();
    }
    static void brokeMieneral(int row, int i)
    {
        //왼쪽에서 던짐
        if(i%2==0)
        {
            for(int cal=0; cal<c; cal++)
            {
                if(map[row][cal] == 'x')
                {
                    map[row][cal] = '.';
                    break;
                }
            }
        }
        //오른쪽에서 던짐
        else
        {
            for(int cal = c-1; cal>=0; cal--)
            {
                if(map[row][cal] == 'x')
                {
                    map[row][cal] = '.';
                    break;
                }
            }
        }
    }
    //공중에 떠있다 -> 바닥에 닿아있지 않다.
    //공중에 떠있는 미네랄 클러스터를 찾음
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        visit = new boolean[r][c];
        //마지막행에서 모든 열을 순회하면서 미네랄이 존재하는 열을 큐에 추가해서 탐색
        for(int i=0; i<c; i++)
        {
            if(map[r-1][i] =='.' || visit[r-1][i]) continue;
            q.add(new int[]{r-1, i});
            visit[r-1][i] = true;

            while (!q.isEmpty())
            {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];
                for(int dir=0; dir<4; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '.') continue;
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                }
            }
        }
        ArrayList<int[]> floatingCluster = new ArrayList<>(); // 공중에 뜬 클러스터의 좌표를 저장
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                //바닥과 붙어있는 클러스터를 탐색했을 때 방문하지 않은 클러스터이면 공중에 떠있음
                if(!visit[i][j] && map[i][j] == 'x')
                {
                    map[i][j] = '.';
                    floatingCluster.add(new int[]{i,j});
                }
            }
        }
        //공중에 떠있는 클러스터가 없으면 함수 종료
        if(floatingCluster.isEmpty()) return;

        int dropDist = Integer.MAX_VALUE;
        for(int[] mineral:floatingCluster)
        {
            int dist = 0;
            int x = mineral[0];
            int y = mineral[1];

            while (true)
            {
                int nX = x + dist + 1;
                if(nX >= r || map[nX][y] == 'x') break;
                dist++;
            }
            dropDist = Math.min(dropDist, dist);
        }

        for(int[] mineral:floatingCluster)
        {
            int x = mineral[0];
            int y = mineral[1];

            map[x+dropDist][y] = 'x';
        }
    }
    static void print()
    {
        StringBuilder sb = new StringBuilder();
        for(char[] c: map)
        {
            for(char h: c)
            {
                sb.append(h);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}