package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_12761
{
    static int a,b,n,m;
    static boolean[] visit = new boolean[100001];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken()); // 탐색 시작지점
        m = Integer.parseInt(st.nextToken()); // 목표 지점
        bfs();
    }

    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{n,0});
        visit[n] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curCnt = cur[1];
            if(curNode == m)
            {
                System.out.print(curCnt);
                return;
            }
            int[] dx = {curNode -1,curNode + 1,curNode -a,curNode +a,curNode -b,curNode +b, curNode * a, curNode * b};
            for(int next:dx)
            {
                if(OOB(next) || visit[next]) continue;
                q.add(new int[]{next, curCnt + 1});
                visit[next] = true;
            }
        }
    }
    static boolean OOB(int x)
    {
        return x < 0 || x > 100000;
    }
}
