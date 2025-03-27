import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            sb.append(bfs(s,t)).append('\n');
        }

        System.out.print(sb);
    }

    static int bfs(int s,int t)
    {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s,t,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curS = cur.s;
            int curT = cur.t;
            int curCnt = cur.cnt;

            if (curS == curT) return curCnt;

            if(curS > curT) continue;

            q.add(new Node(curS * 2, curT + 3, curCnt + 1));
            q.add(new Node(curS + 1, curT, curCnt + 1));
        }
        
        return -1;
    }

    static class Node
    {
        int s;
        int t;
        int cnt;

        public Node(int s, int t, int cnt)
        {
            this.s = s;
            this.t = t;
            this.cnt = cnt;
        }
    }
}
