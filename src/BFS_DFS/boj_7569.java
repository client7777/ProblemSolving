package BFS_DFS;
//M은 가로칸, N은 세로칸
import java.util.*;
import java.io.*;
//3차원 토마토
public class boj_7569
{
    static int m,n,h; // 상자의 가로, 세로, 상자의 높이
    static int[][][] map;
    static int[] dz = {0,0,0,0,-1,1};
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};


    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        //3차원 배열의 인덱스 순서: z, x, y (높이, 행(세로), 열(가로))

        map = new int[h][n][m];
        Queue<int[]> q = new LinkedList<>();

        for(int z = 0; z<h; z++)
        {
            for(int x=0; x<n; x++)
            {
                st = new StringTokenizer(br.readLine());
                for(int y=0; y<m; y++)
                {
                    map[z][x][y] = Integer.parseInt(st.nextToken());
                    if(map[z][x][y] == 1)
                    {
                        q.add(new int[]{z,x,y});
                    }
                }
            }
        }
        //배열을 탐색하면서 토마토를 익히는 연산
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curZ = cur[0];
            int curX = cur[1];
            int curY = cur[2];

            for(int dir= 0; dir<6; dir++)
            {
                int nZ = curZ + dz[dir];
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                //범위 밖이거나 이미 방문한 곳은 탐색 대상 제외
                //방문한 곳도 map 배열에서 체크, 0이 아닌 경우는 -1인 경우이거나 이미 방문해서 배열값이 0보다 큰 경우이다.
                if(OOB(nZ, nX ,nY) || map[nZ][nX][nY] != 0) continue;
                q.add(new int[] {nZ,nX,nY});
                map[nZ][nX][nY] = map[curZ][curX][curY] + 1;
            }
        }

        //익힐 수 있는 토마토를 모두 익히고 나서
        int min_day = 0;
        for(int z=0; z<h; z++)
        {
            for(int x=0; x<n; x++)
            {
                for(int y=0; y<m; y++)
                {
                    // 익지 않은 토마토가 존재할 경우
                    if(map[z][x][y] == 0)
                    {
                        bw.write("-1\n");
                        bw.flush();
                        return;
                    }
                    min_day = Math.max(min_day, map[z][x][y]); // 최소 일수 갱신
                }
            }
        }
        // 초기 익은 토마토들이 1로 시작했기 때문에 최종적으로 계산된 최소 날짜는 실제 일수에 1이 더해진 값이다.
        // 따라서 실제로 걸린 최소 일수를 구하기 위해서는 이 값에서 1을 빼줘야 한다.
        bw.write((min_day == 1 ? 0 : min_day-1) + "\n");
        bw.flush();;
    }
    static boolean OOB(int z, int x, int y)
    {
        return z < 0 || x < 0 || y < 0 || z >= h || x >= n || y >= m;
    }
}
