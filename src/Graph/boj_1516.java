package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1516
{
    static int n;
    static ArrayList<Integer>[] adjList;
    static int[] time,ans,inDegree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        time = new int[n+1];
        ans = new int[n+1];
        inDegree = new int[n+1];

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens())
            {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) break;
                adjList[num].add(i);
                inDegree[i]++;
            }
        }
        topologicalSort();
        System.out.print(sb);
    }
    static void topologicalSort()
    {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++)
        {
            if(inDegree[i] == 0)
            {
                q.add(i);
            }
        }
        while (!q.isEmpty())
        {
            int cur = q.poll();
            ans[cur] += time[cur];
            for(int next:adjList[cur])
            {
                inDegree[next]--;
                if(inDegree[next] == 0)
                {
                    q.add(next);
                }
                ans[next] = Math.max(ans[next], ans[cur]);
            }
        }
        for(int i=1; i<=n; i++)
        {
            sb.append(ans[i]).append('\n');
        }
    }
}
