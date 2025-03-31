import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main
{
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visit = new boolean[5][5];
    static ArrayList<Pos> position = new ArrayList<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<5; i++)
        {
            String str = br.readLine();
            for(int j=0; j<5; j++)
            {
                if(str.charAt(j) == '*')
                {
                    // 조각의 위치를 저장
                    position.add(new Pos(i,j));
                }
            }
        }

        backTrack(0,0);
        System.out.print(answer);
    }

    static void backTrack(int depth, int sum)
    {
        if(answer <= sum) return;

        if(depth == position.size())
        {
            if(bfs())
            {
                answer = Math.min(answer, sum);
            }

            return;
        }

        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                if(visit[i][j]) continue;
                //맨해튼 거리
                int dist = Math.abs(position.get(depth).x - i) + Math.abs(position.get(depth).y - j);
                visit[i][j] = true;
                backTrack(depth + 1, sum + dist);
                visit[i][j] = false;
            }
        }
    }

    static boolean bfs()
    {
        int cnt = 0;
        boolean[][] visited = new boolean[5][5];
        boolean find = false;

        Queue<Pos> q = new LinkedList<>();

        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                if(visit[i][j] && !find)
                {
                    q.add(new Pos(i,j));
                    visited[i][j] = true;
                    find = true;
                }
            }
        }

        while (!q.isEmpty())
        {
            Pos cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            cnt++;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visited[nX][nY] || !visit[nX][nY]) continue;

                visited[nX][nY] = true;
                q.add(new Pos(nX,nY));
            }
        }

        return cnt == position.size();
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > 4 || y > 4;
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
