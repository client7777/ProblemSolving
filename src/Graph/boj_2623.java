package Graph;

import java.io.*;
import java.util.*;

public class boj_2623
{
    static int n,m;
    static int[] inDegree;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }
        inDegree = new int[n+1];

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());

            for(int j=0; j<cnt-1; j++)
            {
                int next = Integer.parseInt(st.nextToken());
                graph[prev].add(next);
                inDegree[next]++;
                prev = next;
            }
        }
        topologicalSort();
    }
    static void topologicalSort()
    {
        int used = 0;
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
            sb.append(cur).append('\n');
            used++;
            for(int next:graph[cur])
            {
                inDegree[next]--;
                if(inDegree[next] == 0)
                {
                    q.add(next);
                }
            }
        }
        if(used == n)
        {
            System.out.print(sb);
        }
        else
            System.out.print(0);
    }
}
