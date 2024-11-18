package BFS_DFS;
//아기 상어
import java.io.*;
import java.util.*;

public class boj_16236
{
    static int n;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<int[]> q = new LinkedList<>();
    static int sharkSize = 2; // 아기상어의 초기 크기
    static int eatCount = 0; // 먹은 물고기 수
    static int time = 0; // 총 소요 시간

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9)
                {
                    // 상어의 초기 좌표와 거리
                    q.add(new int[]{i,j,0});
                    map[i][j] = 0;
                }
            }
        }

        while (true)
        {
            int[] target = findPrey();
            if(target == null)  break;

            //물고기 먹기
            int preyX = target[0];
            int preyY = target[1];
            int preyDist = target[2];

            time += preyDist; // 먹는데 걸리는 시간 추가
            eatCount++; // 물고기 먹음
            map[preyX][preyY] = 0; // 먹이를 먹은 칸은 빈칸으로 갱신
            if(eatCount == sharkSize)
            {
                //상어의 사이즈만큼 먹이를 먹으면
                sharkSize++;
                eatCount = 0; // 먹은 물고기의 개수 초기화
            }
            //먹이를 먹은 칸에서 다시 탐색 시작
            q.add(new int[]{preyX, preyY, 0});
        }
        System.out.print(time);
    }
    static int[] findPrey()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->
        {
            //찾은 먹이의 좌표를 우선순위에 맞게 정렬
            if(a[2] != b[2]) return a[2] - b[2]; // 짧은 거리 우선
            if(a[0] != b[0]) return a[0] - b[0]; // 상단 우선
            return a[1] - b[1]; // 좌측 우선
        });
        boolean[][] visit = new boolean[n][n];
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            
            for(int dir = 0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] > sharkSize) continue;
                visit[nX][nY] = true;
                q.add(new int[]{nX,nY,curDist + 1});

                if(map[nX][nY] != 0 && map[nX][nY] < sharkSize)
                {
                    pq.add(new int[]{nX,nY, curDist + 1});
                }
            }
        }

        //우선순위 큐가 비어있다면, 즉 먹이를 찾지 못했다면
        if(pq.isEmpty()) return null;
        //찾은 먹이의 좌표를 (x,y) 쌍으로 리턴
        return pq.poll();
    }
    static boolean OOB (int x, int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
