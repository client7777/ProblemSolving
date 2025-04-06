import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static ArrayList<int[]>[] adList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y  =Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adList[x].add(new int[]{y,w});
            adList[y].add(new int[]{x,w});
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bfs(u,v);
        }
        System.out.print(sb);
    }
    static void bfs(int start, int end)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        boolean[] visit = new boolean[n+1];
        visit[start] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curDist = cur[1];
            if(curX == end)
            {
                sb.append(curDist).append('\n');
                return;
            }
            for(int[] next:adList[curX])
            {
                int nextNode = next[0];
                int nextDist = next[1];
                if(!visit[nextNode])
                {
                    visit[nextNode] = true;
                    q.add(new int[]{nextNode, curDist + nextDist});
                }
            }
        }
    }
}
