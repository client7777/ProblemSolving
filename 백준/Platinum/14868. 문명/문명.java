import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,k;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<Node> q = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        for(int i=0; i<k; i++)
        {
            //문명 시작지의 좌표
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            q.add(new Node(x,y));
            //문명 지역임을 표시
            map[x][y] = 77; 
        }

        int day = 0;
        while (true)
        {
            if(count() == 1) break;
            bfs();
            day++;
        }

        System.out.print(day);
    }

    static void bfs()
    {
        Queue<Node> nextQ = new LinkedList<>(); // 다음날 문명을 전파할 시작지점
        
        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == 77) continue; // 범위 밖이거나 이미 문명 지역이면 무시
                map[nX][nY] = 77; // 문명이 전파되었으므로 문명 지역으로 표시해줌
                nextQ.add(new Node(nX,nY));
            }
        }

        q = nextQ;
    }

    //문명이 하나로 연결되었는지 판단하는 메서드
    static int count()
    {
        int returnVal = 0;

        Queue<Node> q = new LinkedList<>();
        boolean[][] visit = new boolean[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(!visit[i][j] && map[i][j] == 77)
                {
                    returnVal++;
                    visit[i][j] = true;
                    q.add(new Node(i,j));
                    while (!q.isEmpty())
                    {
                        Node cur = q.poll();
                        int curX = cur.x;
                        int curY = cur.y;

                        for(int dir=0; dir<4; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];

                            if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] != 77) continue;
                            q.add(new Node(nX,nY));
                            visit[nX][nY] = true;
                        }
                    }
                }
            }
        }

        return returnVal;
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
        return x < 1 || y < 1 || x > n || y > n;
    }
}
