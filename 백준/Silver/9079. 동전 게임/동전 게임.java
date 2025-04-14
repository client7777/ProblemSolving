import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static boolean[] visit;
    static int[][] map;
    static int[][] flipIndex = {
            {0,1,2}, //row 0
            {3,4,5}, //row 1
            {6,7,8}, //row 2
            {0,3,6}, //col 0
            {1,4,7}, //col 1
            {2,5,8}, //col 2
            {0,4,8}, //대각 1
            {2,4,6}, //대각 2
    };
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        while (test_case -- > 0)
        {
            map = new int[3][3];
            visit = new boolean[1 << 9];

            for(int i=0; i<3; i++)
            {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<3; j++)
                {
                    String coin = st.nextToken();

                    if(coin.equals("H"))
                    {
                        map[i][j] = 0;
                    }
                    else
                    {
                        map[i][j] = 1;
                    }
                }
            }

            int state = 0;
            for(int i=0; i<3; i++)
            {
                for(int j=0; j<3; j++)
                {
                    if(map[i][j] == 1)
                    {
                        state |= (1 << (i * 3 + j));
                    }
                }
            }

            sb.append(bfs(state)).append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(int state)
    {
        visit[state] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(state, 0));

        int[] flipMasks = getFlipMask();

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curState = cur.state;
            int curCnt = cur.cnt;

            if(curState == 0 || curState == (1 << 9) - 1) return curCnt;

            for(int mask : flipMasks)
            {
                int nextState = curState ^ mask;

                if(!visit[nextState])
                {
                    visit[nextState] = true;
                    q.add(new Node(nextState, curCnt + 1));
                }
            }
        }

        return -1;
    }

    static int[] getFlipMask()
    {
        int[] masks = new int[8];

        for(int i=0; i<8; i++)
        {
            int mask = 0;
            for(int pos : flipIndex[i])
            {
                mask |= (1 << pos);
            }

            masks[i] = mask;
        }

        return masks;
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
}
