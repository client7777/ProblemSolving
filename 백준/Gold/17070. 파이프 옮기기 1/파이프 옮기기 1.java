import java.io.*;
import java.util.*;

public class Main
{
    static int n;
    static int answer = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.print(answer);
    }

    static void bfs()
    {
        Queue<Node> q = new LinkedList<>();
        //1 -> 가로 2 -> 세로 3 -> 대각
        q.add(new Node(0,1,1));
        
        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDir = cur.dir;
            
            if(curX == n-1 && curY == n-1)
            {
                answer++;
                continue;
            }
            
            //현재 방향이 가로이거나 대각이면 가로로 이동 가능
            if(curDir == 1 || curDir == 3)
            {
                if(curY + 1 < n && map[curX][curY + 1] == 0)
                {
                    q.add(new Node(curX, curY + 1, 1));
                }
            }

            //현재 방향이 세로이거나 대각이면 세로로 이동 가능
            if(curDir == 2 || curDir == 3)
            {
                if(curX + 1 < n && map[curX + 1][curY] == 0)
                {
                    q.add(new Node(curX + 1, curY, 2));
                }
            }

            //대각으로 이동은 현재 방향에 상관없이 가능
            if(curX + 1 < n && curY + 1 < n)
            {
                if(map[curX + 1][curY + 1] == 0 && map[curX + 1][curY] == 0 && map[curX][curY + 1] == 0)
                {
                    q.add(new Node(curX + 1, curY + 1, 3));
                }
            }
        }
    }

    static class Node
    {
        int x;
        int y;
        int dir;

        public Node(int x, int y, int dir)
        {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
