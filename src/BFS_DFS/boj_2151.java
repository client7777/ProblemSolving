package BFS_DFS;
//거울을 45도 기울여서 설치하면 빛이 시계 방향이나 반시계 방향으로 90도 굴절됨.
//거울 설치가 가능한 지점에서는 거울을 설치하거나 설치하지 않을 수 있음 그 외 빛이 빈공간은 큐에 추가해서 탐색을 이어감
import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_2151
{   
    static int n;
    static int startX=0,startY=0,endX=0,endY=0;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '#')
                {
                    if(startX == 0 && startY == 0)
                    {
                        startX = i;
                        startY = j;
                    }
                    else
                    {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }
        bfs();
    }
    static void bfs()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[3]));
        boolean[][][] visit = new boolean[n][n][4];
        //시작 좌표에 대해서 4방향 모두 큐에 추가
        for(int i=0; i<4; i++)
        {
            pq.add(new int[]{startX, startY, i, 0});  // 시작좌표, 방향, 거울의 개수
        }
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDir = cur[2];
            int curCnt = cur[3];
            visit[curX][curY][curDir] = true;
            if(curX == endX && curY == endY)
            {
                System.out.print(curCnt);
                return;
            }
            int nX = curX + dx[curDir];
            int nY = curY + dy[curDir];
            if(OOB(nX,nY) || visit[nX][nY][curDir] || map[nX][nY] == '*') continue;
            //거울을 설치하는 경우
            if(map[nX][nY] == '!')
            {   
                int leftDir = (curDir + 3) % 4;
                int rightDir= (curDir + 1) % 4;
                pq.add(new int[]{nX,nY,leftDir, curCnt + 1});
                pq.add(new int[]{nX,nY,rightDir,curCnt + 1});
            }
            //거울을 설치하지 않는 경우
            pq.add(new int[]{nX,nY,curDir,curCnt});
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
