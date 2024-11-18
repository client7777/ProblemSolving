package BFS_DFS;
//출발지점에서 목표지점까지 최소한의 비용으로 도달하게 하는 알고리즘 -> 다익스트라
import java.io.*;
import java.util.*;

public class boj_6087
{
    static int w,h;
    static char[][] map;
    //각 위치별로 4방향에 대해 필요한 거울의 개수를 저장
    static int[][][] dist;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<int[]> cPoint = new ArrayList<>();
    static int startX, startY, endX, endY;
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        map = new char[h][w];
        dist = new int[h][w][4];

        for(int i=0; i<h; i++)
        {
            String str = br.readLine();
            for(int j=0; j<w; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C') cPoint.add(new int[]{i,j});
            }
        }

        startX = cPoint.get(0)[0];
        startY = cPoint.get(0)[1];
        endX = cPoint.get(1)[0];
        endY = cPoint.get(1)[1];
        dijkstra();
    }
    static void dijkstra()
    {
        //우선순위 큐 생성, 거울의 개수를 기준으로 오름차순 정렬, 거울 개수가 적은 경로부터 먼저 탐색
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{startX, startY, 0, -1}); 
        //dist 배열 초기화
        for(int i=0; i<h; i++)
        {
            for(int j=0; j<w; j++)
            {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        //시작점은 방향에 상관없이 거울의 개수 0
        for(int i=0; i<4; i++)
        {
            dist[startX][startY][i] = 0;
        }
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];
            int curDir = cur[3];
            if(curX == endX && curY == endY)
            {
                System.out.print(curCnt);
                return;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == '*') continue;

                int newMirror = curCnt;

                // 첫 시작점에서는 방향이 없기 때문에 -1, 거울을 추가하지 않음, 현재 이동방향과 다른 방향이라면 거울을 추가함
                if(curDir != -1 && curDir != dir) newMirror++;
                //기존에 저장된 값보다 거울을 적게 설치했을 경우 업데이트
                //nX,nY 좌표에 dir 방향으로 도달했을 때 설치한 거울의 최소 개수
                //새롭게 도착한 경로에서 더 적은 개수를 사용했다면 더 적은 거울을 사용한 경로로 갱신
                if(dist[nX][nY][dir] > newMirror)
                {
                    dist[nX][nY][dir] = newMirror; // 해당 좌표에 대한 최소 거울 개수를 갱신
                    pq.add(new int[]{nX,nY,newMirror, dir});
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= h || y >= w;
    }
}
