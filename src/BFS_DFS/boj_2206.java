package BFS_DFS;
//벽 부수고 이동
import java.io.*;
import java.util.*;
/*
visit 배열을 int형으로 선언해서 방문여부를 체크함과 동시에 최단거리 계산
visit[x][y][0] : 벽을 부수지 않고 (x,y)에 도달한 최소 거리.
visit[x][y][1] : 벽을 부수고 (x,y)에 도달한 최소 거리.
*/

//벽 부수고 이동
import java.io.*;
import java.util.*;
/*
visit 배열을 int형으로 선언해서 방문여부를 체크함과 동시에 최단거리 계산
visit[x][y][0] : 벽을 부수지 않고 (x,y)에 도달한 최소 거리.
visit[x][y][1] : 벽을 부수고 (x,y)에 도달한 최소 거리.
*/
//시간복잡도 = O(2 * n * m ) n*m 격자의 크기에서 수행. wall==1, wall==0 두가지 상태를 추적하므로
public class boj_2206
{
    static int n,m;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m][2];
        for(int i=0;i<n;i++)
        {
            String str = br.readLine();
            for(int j=0;j<m;j++)
            {
                map[i][j] = str.charAt(j)-'0';
            }
        }
        System.out.print(bfs(0,0));
    }
    static int bfs(int x,int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y,0,1}); //x,y,wall,dist
        visit[x][y][0] = true;
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int wall = cur[2];
            int curDist = cur[3];
            if(curX == n-1 && curY == m-1)
            {
                return curDist;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!OOB(nX,nY))
                {
                    //벽이 아닌 경우
                    if(map[nX][nY] == 0 && !visit[nX][nY][wall])
                    {
                        q.add(new int[]{nX,nY,wall,curDist+1});
                        visit[nX][nY][wall] = true;
                    }
                    //벽인 경우
                    if(map[nX][nY] == 1 && wall == 0 && !visit[nX][nY][wall])
                    {
                        q.add(new int[]{nX,nY,1,curDist+1});
                        visit[nX][nY][1] = true;
                    }
                }
            }
        }
        return -1;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}
//map[nX][nY] == 1 && wall == 0 && !visit[nX][nY][wall]
//다음 좌표가 벽이고, 벽을 부수지 않은 상태로 다음 좌표를 방문한 적이 없어야 함
//벽을 부수지 않고 다음 좌표를 방문한 적이 없어야만 벽을 부수고 다음 좌표로 이동할 수 있다.
