package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_14226
{
    static int s;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());
        bfs(s);
    }
    static void bfs(int s)
    {
        //현재 화면에 있는 이모티콘 수, 클립보드에 있는 이모티콘 수
        boolean[][] visit = new boolean[2001][2001];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,0,0}); // 현재 이모티콘 수, 클립보드 이모티콘 수, 연산 횟수
        visit[1][0] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int now = cur[0];
            int save = cur[1];
            int cnt = cur[2];
            if(now == s)
            {
                System.out.print(cnt);
                return;
            }
            //현재 화면에 있는 이모티콘 수를 클립보드에 복사
            //화면에 있는 이모티콘 수를 그대로 클립보드에 복사
            if(now > 0 && !visit[now][now])
            {
                q.add(new int[]{now,now, cnt + 1});
                visit[now][now] = true;
            }
            //클립보드에 1개 이상의 이모티콘이 있을 경우, 클립보드에 있는 이모티콘 수를 화면에 붙여넣음.
            if(save > 0 && now + save < 2000 && !visit[now + save][save])
            {
                q.add(new int[]{now + save, save, cnt + 1});
                visit[now+save][save] = true;
            }
            //현재 화면에 1개 이상의 이모티콘이 있을 때 1개를 삭제
            if(now > 0 && !visit[now-1][save])
            {
                q.add(new int[]{now - 1, save, cnt + 1});
                visit[now - 1][save] = true;
            }
        }
    }
}
