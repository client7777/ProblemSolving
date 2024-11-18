package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11060
{
    static int n;
    static int[] map;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            map[i] = Integer.parseInt(st.nextToken());
        }
        bfs();
    }
    static void bfs()
    {
        visit = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,0});
        visit[1] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int now = cur[0];
            int nowCnt = cur[1];
            if(now == n)
            {
                System.out.print(nowCnt);
                return;
            }
            for(int i=now; i<=now + map[now]; i++)
            {
                if(i > n || visit[i]) continue;
                q.add(new int[]{i, nowCnt + 1});
                visit[i] = true;
            }
        }
        System.out.print(-1);
    }
}
