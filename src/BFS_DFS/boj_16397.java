package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16397
{
    static final int LIMIT = 99999;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());

        bfs(n,t,g);
    }

    static void bfs(int n, int t,int g)
    {
        boolean[] visit = new boolean[LIMIT + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n,0});
        visit[n] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curCnt = cur[1];

            if(curNode == g)
            {
                System.out.print(curCnt);
                return;
            }

            if(curCnt >= t) continue;

            if(curNode + 1 <= LIMIT && !visit[curNode + 1])
            {
                visit[curNode+1] = true;
                q.add(new int[]{curNode + 1, curCnt + 1});
            }

            int b = curNode * 2;

            if(b > LIMIT) continue;

            int nextNode = getNextNode(b);

            if(nextNode <= LIMIT && !visit[nextNode])
            {
                q.add(new int[]{nextNode, curCnt + 1});
                visit[nextNode] = true;
            }
        }
        System.out.print("ANG");
    }
    static int getNextNode(int n)
    {
        if(n == 0)  return 0;

        for(int i=10; true; i *= 10)
        {
            if(i > n)
            {
                n -= i/10;
                break;
            }
        }

        return n;
    }
}
