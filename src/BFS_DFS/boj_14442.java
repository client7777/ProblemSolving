package BFS_DFS;
import java.io.*;
import java.util.*;
//벽 부수고 이동하기 2
//map[n-1][m-1] 까지 가는 최소 비용
//시작과 끝칸의 비용도 포함
//k개의 벽을 부술 수 있음
public class boj_14442
{
    static int n,m,k;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m][k+1]; // 벽을 몇번 부수고 해당 좌표에 방문했는지 기록하기 위한 배열

        for(int i=0;i<n;i++)
        {
            String str = br.readLine();
            for(int j=0;j<m;j++)
            {
                map[i][j] = str.charAt(j)-'0';
            }
        }
        int ans = bfs();
        System.out.println(ans);
    }
    static int bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0,1});
        // 좌표, 벽을 부순 횟수, 거리 시작점도 거리에 포함되기 때문에 초기 시작지점에서의 거리를 1로 설정
        visit[0][0][0] = true;
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int wall = cur[2];
            int dist = cur[3];
            if(curX == n-1 && curY == m-1) return dist;
            for(int dir=0;dir<4;dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!(nX < 0 || nY < 0 || nX >= n || nY >= m))
                {
                    if(map[nX][nY] == 0 && !visit[nX][nY][wall])
                    {
                        q.add(new int[]{nX, nY, wall, dist+1});
                        visit[nX][nY][wall] = true;
                    }
                    if(wall < k && map[nX][nY] == 1 && !visit[nX][nY][wall+1])
                    {
                        q.add(new int[]{nX, nY, wall+1, dist+1});
                        visit[nX][nY][wall+1] = true;
                    }
                }
            }
        }
        return -1; // 목적지에 도달하지 못했다면
    }
}
/*
if (wall < k && map[nX][nY] == 1 && !visit[nX][nY][wall+1])
if (map[nX][nY] == 1 && !visit[nX][nY][wall+1] && wall < k)
두 조건문의 순서 차이 때문에 발생하는 런타임 에러는 short-circuit 평가와 관련이 있습니다.
short-circuit 평가란 조건문을 평가할 때, 논리 연산의 결과가 이미 확정된 경우, 나머지 조건을 평가하지 않는다는 것을 의미합니다.
이 조건문의 경우, wall < k가 false라면 나머지 조건 (map[nX][nY] == 1와 !visit[nX][nY][wall+1])은 평가되지 않습니다.
따라서 wall+1이 k+1보다 큰 값이 되어도 visit[nX][nY][wall+1]에 접근하지 않으므로 배열 범위를 초과하지 않습니다.
이 조건문의 경우, map[nX][nY] == 1이 true이고 !visit[nX][nY][wall+1]을 먼저 평가하려고 시도합니다. 만약 wall이 k인 상태에서 이 조건문을 평가한다면 wall+1이 k+1이 되어 배열의 범위를 초과하게 됩니다.
이로 인해 ArrayIndexOutOfBoundsException이 발생할 수 있습니다.
*/