package Graph;

import java.io.*;
import java.util.*;

public class boj_2660
{
    static int n;
    static ArrayList<Integer>[] adList;
    static int[] ans;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        ans = new int[n+1];
        Arrays.fill(ans, Integer.MIN_VALUE);
        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        while (true)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(x == -1 && y == -1) break;
            adList[x].add(y);
            adList[y].add(x);
        }

        for(int i=1; i<=n; i++)
        {
            bfs(i);
        }
        int minScore = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            minScore = Math.min(minScore, ans[i]);
        }
        int cnt = 0;
        for(int i=1; i<=n; i++)
        {
            if(ans[i] == minScore)
            {
                sb2.append(i + " ");
                cnt++;
            }
        }
        sb.append(minScore + " " + cnt);
        System.out.println(sb);
        System.out.print(sb2);
    }
    static void bfs(int start)
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
            for(int next:adList[curNode])
            {
                if(!visit[next])
                {
                    q.add(new int[]{next, curDist+1});
                    visit[next] = true;
                    ans[next] = Math.max(ans[next],curDist+1 );
                }
            }
        }
    }

}

