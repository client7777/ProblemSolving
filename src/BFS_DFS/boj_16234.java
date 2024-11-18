package BFS_DFS;
//인구 이동 시간복잡도 = O(2000 * n^4)
import java.util.*;
import java.io.*;

public class boj_16234
{
    static int n,l,r;
    static int[][] map;
    static int day = 0;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken() );
            }
        }
        
        while (true)
        {
            boolean isMoved = false;
            boolean[][] visit = new boolean[n][n];
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    //새로운 연합이 될 수 있는 땅을 발견했다면
                    if(!visit[i][j])
                    {
                        if(bfs(i,j,visit))
                        {
                            //인구 이동이 이루어졌다면
                            isMoved = true;
                        }
                    }
                }
            }
            //인구 이동이 이루어지지 않았다면 반복문 탈출
            if(!isMoved) break;
            day++;
        }
        System.out.print(day);
    }

    static boolean bfs(int start, int end, boolean[][] visit)
    {
        Queue<int[]> q = new LinkedList<>();
        //연합을 찾기위한 큐
        ArrayList<int[]> union = new ArrayList<>();
        //발견한 연합의 좌표를 저장할 리스트
        visit[start][end] = true;
        q.add(new int[]{start, end});
        union.add(new int[]{start, end});
        int sum = map[start][end];
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
                if(OOB(nX,nY) || visit[nX][nY]) continue;

                int diff = Math.abs(map[curX][curY] - map[nX][nY]);
                if(diff >= l && diff <= r)
                {
                    q.add(new int[]{nX,nY});
                    union.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                    area++;
                    sum += map[nX][nY];
                }
            }
        }
        // 국경을 열어 연합을 형성하지 못했으므로 false 반환
        if(area == 1) return false;

        int newPop = sum / area;
        for(int[] pos:union)
        {
            int x = pos[0];
            int y = pos[1];
            map[x][y] = newPop;
        }
        return true;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
