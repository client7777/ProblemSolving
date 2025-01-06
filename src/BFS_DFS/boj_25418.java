package BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_25418
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(a,k);
    }
    static void bfs(int a, int k)
    {
        boolean[] visit = new boolean[k+1];
        visit[a] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a,0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNum = cur[0];
            int curCnt = cur[1];

            if(curNum == k)
            {
                System.out.print(curCnt);
                return;
            }
            if(curNum * 2 <= k && !visit[curNum * 2])
            {
                q.add(new int[]{curNum * 2 , curCnt + 1});
                visit[curNum * 2] = true;
            }
            if(curNum + 1 <= k && !visit[curNum + 1])
            {
                q.add(new int[]{curNum + 1, curCnt + 1});
                visit[curNum + 1] = true;
            }
        }
    }
}
