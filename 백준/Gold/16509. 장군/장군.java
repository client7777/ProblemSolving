import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int startX, startY;
    static int endX, endY;
    static int[] dx = {-1,0,1,0}; //상 -> 우 -> 하 -> 좌
    static int[] dy = {0,1,0,-1};
    static int[] cx = {-1,-1,1,1}; //좌상 -> 우상 -> 좌하 -> 우하
    static int[] cy = {-1,1,1,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        System.out.print(bfs());

    }

    static int bfs()
    {
        boolean[][] visit = new boolean[10][9];
        visit[startX][startY] = true;
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curCnt = cur.cnt;

            if(curX == endX && curY == endY) return curCnt;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || nX == endX && nY == endY) continue;

                for(int i=0; i<2; i++)
                {
                    int cross = (dir + i) % 4;
                    int nnX = nX + cx[cross];
                    int nnY = nY + cy[cross];

                    if(nnX == endX && nnY == endY) continue;

                    nnX += cx[cross];
                    nnY += cy[cross];

                    if(OOB(nnX,nnY) || visit[nnX][nnY]) continue;

                    visit[nnX][nnY] = true;
                    q.add(new Node(nnX,nnY,curCnt + 1));
                }
            }
        }

        return -1;
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > 9 || y > 8;
    }
    static class Node
    {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt)
        {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
