import java.io.*;
import java.util.*;

public class Main
{
    static int[] dx = {0,-1,0,1,0};
    static int[] dy = {0,0,1,0,-1};
    static final char BLACK = '*';
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            int board = 0;

            for(int i=0; i<3; i++)
            {
                String str = br.readLine();
                for(int j=0; j<3; j++)
                {
                    if(str.charAt(j) == BLACK)
                        board |= (1 << (i * 3 + j));
                }
            }

            sb.append(bfs(board)).append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(int start)
    {
        boolean[] visit = new boolean[1 << 9];
        visit[start] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curState = cur.state;
            int curCnt = cur.cnt;

            if(curState == 0) return curCnt;

            for(int i=0; i<3; i++)
            {
                for(int j=0; j<3; j++)
                {
                    int nextState = curState;

                    for(int dir=0; dir<5; dir++)
                    {
                        int nX = i + dx[dir];
                        int nY = j + dy[dir];

                        if(OOB(nX,nY)) continue;

                        int pos = nX * 3 + nY;

                        nextState ^= (1 << pos);
                    }

                    if(!visit[nextState])
                    {
                        visit[nextState] = true;
                        q.add(new Node(nextState, curCnt + 1));
                    }
                }
            }
        }

        return -1;
    }

    static class Node
    {
        int state;
        int cnt;

        public Node(int state, int cnt)
        {
            this.state = state;
            this.cnt = cnt;
        }
    }

    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x > 2 || y > 2;
    }
}
