package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14248
{
    static int n;
    static int[] step;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        step = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            step[i] = Integer.parseInt(st.nextToken());
        }

        int s = Integer.parseInt(br.readLine());

        bfs(s);
    }
    static void bfs(int start)
    {
        boolean[] visit = new boolean[n+1];
        visit[start] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            if(!OOB(cur + step[cur]) && !visit[cur + step[cur]])
            {
                q.add(cur + step[cur]);
                visit[cur + step[cur]] = true;
            }
            if(!OOB(cur - step[cur]) && !visit[cur - step[cur]])
            {
                q.add(cur - step[cur]);
                visit[cur - step[cur]] = true;
            }
        }

        int cnt = 0;
        for(boolean flag : visit)
        {
            if(flag) cnt++;
        }

        System.out.print(cnt);
    }
    static boolean OOB(int x)
    {
        return x < 1 || x > n;
    }
}
