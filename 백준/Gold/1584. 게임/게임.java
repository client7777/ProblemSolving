import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int[][] map = new int[501][501];
    static boolean[][] visit = new boolean[501][501];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine()); // 위험한 구역의 수

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int k=Math.min(x1,x2); k<=Math.max(x1,x2); k++)
            {
                for(int l=Math.min(y1,y2); l<=Math.max(y1,y2); l++)
                {
                    map[k][l] = 1; //위험구역
                }
            }
        }
        
        int m = Integer.parseInt(br.readLine()); // 죽음의 구역의 수

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int k=Math.min(x1,x2); k<=Math.max(x1,x2); k++)
            {
                for(int l=Math.min(y1,y2); l<=Math.max(y1,y2); l++)
                {
                    map[k][l] = 2; // 죽음 구역
                }
            }
        }
        System.out.print(bfs());
    }
    static int bfs()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{0,0,0});
        visit[0][0] = true;

        while (!pq.isEmpty())
        {
            int[] cur =pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cur[2];
            if(curX == 500 && curY == 500)
            {
                return curCost;
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 2) continue;
                visit[nX][nY] = true;
                pq.add(new int[]{nX,nY,map[nX][nY] == 1 ? curCost + 1 : curCost});
            }
        }
        return -1;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > 500 || y > 500;
    }
}
