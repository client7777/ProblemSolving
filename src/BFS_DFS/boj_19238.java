package BFS_DFS;
// O(m * n^2) 승객 1명 = 2 * bfs = 2 * n^2 총 m명의 승객 = m * n^2 상수인 2는 무시
import java.io.*;
import java.util.*;

public class boj_19238
{
    static int n,m,fuel;
    static int taxiX, taxiY;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<int[]> start = new ArrayList<>();
    static ArrayList<int[]> end = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxiX = Integer.parseInt(st.nextToken());
        taxiY = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            map[startX][startY] = i + 2; //승객에게 번호 부여
            start.add(new int[]{startX, startY});
            end.add(new int[]{endX, endY});
        }
        for(int i=0; i<m; i++)
        {
            if(!findAndGoDestination())
            {
                System.out.print(-1);
                return;
            }
        }
        System.out.print(fuel);
    }
    static boolean findAndGoDestination()
    {
        // 승객의 정보를 받아옴
        int passengerData = findClosestPassenger();
        if(passengerData == -1) return false;
        int passengerIdx = passengerData;
        int sx = start.get(passengerIdx)[0];
        int sy = start.get(passengerIdx)[1];
        int ex = end.get(passengerIdx)[0];
        int ey = end.get(passengerIdx)[1];

        int distToPassenger = bfs(taxiX, taxiY, sx, sy);
        if(distToPassenger == -1 || distToPassenger > fuel) return false;
        fuel -= distToPassenger;
        taxiX = sx;
        taxiY = sy;
        
        int distToDestination = bfs(taxiX, taxiY, ex, ey);
        if(distToDestination == -1 || distToDestination > fuel) return false;
        fuel -= distToDestination;
        fuel += distToDestination * 2;
        taxiX = ex;
        taxiY = ey;
        
        //맵에서 도착지점에 도착한 승객을 제거
        map[sx][sy] = 0;

        return true;
        
    }
    static int findClosestPassenger()
    {
        boolean[][] visit = new boolean[n+1][n+1];
        Queue<int[]> q = new LinkedList<>();
        visit[taxiX][taxiY] = true;
        q.add(new int[]{taxiX, taxiY, 0});

        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2) -> {
            // 우선순위 설정. 거리 -> 행 -> 열
            if(p1[2] != p2[2]) return p1[2] - p2[2];
            if(p1[0] != p2[0]) return p1[0] - p2[0];
            return p1[1] - p2[1];
        });

        int minDist = Integer.MAX_VALUE;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            //승객을 만난 경우
            if(map[curX][curY] >= 2)
            {
                //현재 거리가 최소 거리보다 크다면 원하는 경우가 아니므로 무시
                if(curDist > minDist) continue;
                else
                {
                    //큐에 좌표와 거리와 승객의 인덱스를 추가하고 최소 거리를 갱신
                    int passengerIdx = map[curX][curY] - 2;
                    //좌표와 거리에 따라서 큐에서 우선순위를 정하기 때문에 좌표와 거리는 필요한 정보임
                    pq.add(new int[]{curX, curY, curDist, passengerIdx});
                    minDist = curDist;
                }
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1) continue;
                q.add(new int[]{nX,nY,curDist + 1});
                visit[nX][nY] = true;
            }
        }
        //우선순위 큐가 비어있으면 -1 리턴, 아니라면 승객의 번호를 리턴
        return pq.isEmpty() ? -1 : pq.poll()[3];
    }
    static int bfs(int startX, int startY, int endX, int endY)
    {
        boolean[][] visit = new boolean[n+1][n+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});
        visit[startX][startY] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            if(curX == endX && curY == endY)
            {
                return curDist;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 1) continue;
                q.add(new int[]{nX,nY,curDist +1});
                visit[nX][nY] = true;
            }
        }
        return -1;
    }
    static boolean OOB(int x,int y)
    {
        return  x < 1 || y < 1 || x > n || y > n;
    }
}
