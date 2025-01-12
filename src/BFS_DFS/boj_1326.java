package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1326
{
    static int n;
    static int[] step;
    static int start, end;
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

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        if(start == end)
        {
            System.out.print(0);
            return;
        }

        bfs();
    }

    static void bfs()
    {
        boolean[] visit = new boolean[n+1];
        visit[start] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(curNode == end)
            {
                System.out.print(curDist);
                return;
            }
            for(int i=curNode; i<=n; i+=step[curNode])
            {
                if(visit[i]) continue;
                q.add(new int[]{i, curDist + 1});
                visit[i] = true;
            }
            for(int i=curNode; i > 0; i -= step[curNode])
            {
                if(visit[i]) continue;
                q.add(new int[]{i, curDist + 1});
                visit[i] = true;
            }
        }
        System.out.print(-1);
    }
}
