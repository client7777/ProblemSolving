import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int w,h;
    static int[][] map, count;
    static boolean[][] visit;
    static int[][] dirOdd = {{0,-1}, {0,1}, {-1,0}, {-1,1}, {1,0}, {1,1}}; // 홀
    static int[][] dirEven = {{0,-1}, {0,1}, {-1,-1}, {-1,0}, {1,-1}, {1,0}}; // 짝
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        //외부에서 bfs를 시작하여 내부의 빈공간과 접하는 외곽을 확인하기 위해 맵의 크기를 확장
        map = new int[h+2][w+2];
        count = new int[h+2][w+2];
        visit = new boolean[h+2][w+2];

        for(int i=1; i<=h; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=w; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1)
                {
                    visit[i][j] = true;
                }
            }
        }

        bfs();
        
        int ans = 0;
        for(int i=0; i<h+2; i++)
        {
            for(int j=0; j<w+2; j++)
            {
                ans += count[i][j];
            }
        }

        System.out.print(ans);
    }

    static void bfs()
    {
        visit[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            
            for(int dir=0; dir<6; dir++)
            {
                int nX = 0;
                int nY = 0;
                
                if(curX % 2 == 0)
                {
                    //현재 짝수행이라면
                    nX = curX + dirEven[dir][0];
                    nY = curY + dirEven[dir][1];
                }
                else
                {
                    //현재 홀수행이라면
                    nX = curX + dirOdd[dir][0];
                    nY = curY + dirOdd[dir][1];
                }

                if(OOB(nX,nY)) continue;

                if(map[nX][nY] == 1)
                {
                    count[curX][curY]++;
                    continue;
                }

                if(visit[nX][nY] || map[nX][nY] != 0) continue;

                q.add(new Node(nX,nY));
                visit[nX][nY] = true;

            }
        }
    }

    static class Node
    {
        int x;
        int y;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > h+1 || y > w+1;
    }
}
