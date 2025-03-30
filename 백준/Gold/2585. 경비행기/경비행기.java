import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,k;
    static Node[] nodes;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 출발점과 도착점을 제외한 n개의 노드
        k = Integer.parseInt(st.nextToken()); // 급유 제한 횟수

        nodes = new Node[n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(x,y);
        }

        int left = 0;
        int right = calFuel(0,0,10000,10000);
        int ans = 0;
        while (left <= right)
        {
            int mid = (left + right) / 2;
            if(bfs(mid))
            {
                ans = mid;
                right = mid -1;
            }
            else left = mid + 1;
        }

        System.out.print(ans);
    }

    static boolean bfs(int limit)
    {
        boolean[] visit = new boolean[n];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,0)); // x,y,cnt

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curCnt = cur.cnt;

            if(calFuel(curX, curY, 10000, 10000) <= limit) return true;

            if(curCnt == k) continue;

            for(int i=0; i<n; i++)
            {
                int nextX = nodes[i].x;
                int nextY = nodes[i].y;

                if(visit[i] || calFuel(curX, curY, nextX, nextY) > limit) continue;

                visit[i] = true;
                q.add(new Node(nextX, nextY, curCnt + 1));
            }
        }

        return false;
    }

    static int calFuel(int x1, int y1, int x2, int y2)
    {
        // 거리
        double dist = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        return dist / 10 == (int)dist / 10 ? (int)dist / 10 : (int)dist/10 + 1;
    }

    static class Node
    {
        int x;
        int y;
        int cnt;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int cnt)
        {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}