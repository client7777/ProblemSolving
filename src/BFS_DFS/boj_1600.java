package BFS_DFS;
//말이 되고싶은 원숭이
//BFS의 시간복잡도는 큐에 들어가는 노드의 개수와 동일 각 노드는 한 번만 방문되므로 최대 상태 개수에 각 상태에서의 이동 가능성을 곱해줌
//가능한 상태의 총 개수는 h*w*(k+1) 각 상태에 최대 12개의 노드가 큐에 추가될 수 있음. O(12 * h*w*(k+1))
import java.io.*;
import java.util.*;
//원숭이는 총 k번 나이트의 이동이 가능
public class boj_1600
{
    static int[][] map;
    static boolean[][][] visit;
    static int k,w,h;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int[] night_dx = {-2,-2,2,2,-1,1,-1,1};
    static int[] night_dy = {-1,1,-1,1,2,2,-2,-2};
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); // 나이트 이동횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken()); //열
        h = Integer.parseInt(st.nextToken()); //행

        map = new int[h][w];
        visit = new boolean[h][w][k+1];
        // k번 나이트 이동이 가능하므로 인덱스 k를 참조해야 하므로 배열의 크기를 k+1로 지정

        for(int i=0;i<h;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<w;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = bfs();
        System.out.println(ans);
    }
    //출발지 -> 0,0 목적지 -> h-1,w-1
    static int bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        visit[0][0][k] = true;
        q.add(new int[]{0,0,k,0});
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int remain_K = cur[2];
            int curDist = cur[3];

            if(curX == h-1 && curY == w-1)
            {
                return curDist;
            }

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY][remain_K] || map[nX][nY] == 1) continue;
                q.add(new int[]{nX,nY,remain_K, curDist+1});
                visit[nX][nY][remain_K] = true;
            }
            if(remain_K > 0)
            {
                for(int dir=0; dir<8; dir++)
                {
                    int nX = curX + night_dx[dir];
                    int nY = curY + night_dy[dir];
                    if(OOB(nX,nY) || visit[nX][nY][remain_K -1 ] || map[nX][nY] == 1) continue;
                    q.add(new int[]{nX,nY,remain_K-1,curDist+1});
                    visit[nX][nY][remain_K-1] = true;
                }
            }
        }
        return -1;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y  < 0 || x >= h || y >= w;
    }
}
// 3차원 방문배열을 사용한 이유는 다른 경로로 같은 좌표로 도달한 경우 명확히 구분하기 위해서
// 나이트 이동을 시도할 경우 남은 이동횟수가 k라고 하면 남은 나이트 이동 횟수는 k-1이 됨
// 나이트 이동을 시도하는 좌표가 유효하고 장애물이 없는지 남은 나이트 이동 횟수로 방문하지 않았는지 체크
// 나이트 이동에 성공할 경우 visit 배열 k-1을 true로 설정