package Graph;

import java.io.*;
import java.util.*;

public class boj_6118
{
    static int n,m;
    static ArrayList<Integer>[] adList;
    static boolean[] visit;
    static int[] ans;
    static int max = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[n+1];
        ans = new int[n+1];
        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        //간선의 개수는 m개
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //무방향 그래프
            adList[x].add(y);
            adList[y].add(x);
        }
        bfs();
        int cnt = 0;
        int idx = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++)
        {
            if(max == ans[i])
            {
                cnt++;
                idx = Math.min(idx, i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(idx + " ").append(max + " ").append(cnt);
        System.out.print(sb);
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,0});
        visit[1] = true;
        ans[1] = 0;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            for(int next : adList[curNode])
            {
                if(!visit[next])
                {
                    q.add(new int[]{next, curDist + 1});
                    visit[next] = true;
                    ans[next] = curDist + 1;
                    //그래프 내에서 최대 거리를 구함
                    max = Math.max(max, ans[next]);
                }
            }
        }
    }
}
