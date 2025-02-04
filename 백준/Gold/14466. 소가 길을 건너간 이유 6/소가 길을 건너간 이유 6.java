//길을 건너지 않으면 만날 수 없는 소가 몇쌍인지
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,k,r;
    static int ans = 0;
    static Pos[] cowPos;
    static ArrayList<Pos>[][] road;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        cowPos = new Pos[n+1];

        road = new ArrayList[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                road[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<r; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            road[x1][y1].add(new Pos(x2,y2));
            road[x2][y2].add(new Pos(x1,y1));
        }

        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cowPos[i] = new Pos(x,y);
        }

        for(int i=0; i<k; i++)
        {
            for(int j=i+1; j<k; j++)
            {
                int startX = cowPos[i].x;
                int startY = cowPos[i].y;

                int endX = cowPos[j].x;
                int endY = cowPos[j].y;

                if(!bfs(startX, startY, endX, endY)) ans++; // 다리를 건너지 않고 다른 소한테 도착할 수 없을 때 증가
            }
        }

        System.out.print(ans);
    }
    static boolean bfs(int startX, int startY, int endX, int endY)
    {
        boolean[][] visit = new boolean[n+1][n+1];
        visit[startX][startY] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            if(curX == endX && curY == endY) return true;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY]) continue;

                boolean flag = false;

                for(Pos r:road[curX][curY])
                {
                    if(nX == r.x && nY == r.y)
                    {
                        flag = true;
                        break;
                    }
                }

                if(!flag)
                {
                    visit[nX][nY] = true;
                    q.add(new int[]{nX,nY});
                }
            }
        }
        return false;
    }
    static boolean OOB(int x,int y)
    {
        return  x < 1 || y < 1 || x > n || y > n;
    }
    static class Pos
    {
        int x;
        int y;

        public Pos(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
