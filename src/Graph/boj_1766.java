package Graph;

import java.io.*;
import java.util.*;

public class boj_1766
{
    static int n,m;
    static int[] inDegree;
    static ArrayList<Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        inDegree = new int[n+1];
        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            inDegree[v]++;
        }
        topologicalSort();
        System.out.print(sb);
    }
    static void topologicalSort()
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->o));
        for(int i=1; i<=n; i++)
        {
            if(inDegree[i] == 0)
            {
                pq.add(i);
            }
        }
        while (!pq.isEmpty())
        {
            int cur = pq.poll();
            sb.append(cur).append(" ");
            for(int next:adjList[cur])
            {
                inDegree[next]--;
                if(inDegree[next] == 0)
                    pq.add(next);
            }
        }
    }
}
