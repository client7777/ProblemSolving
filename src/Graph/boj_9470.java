package Graph;

import java.io.*;
import java.util.*;

public class boj_9470
{
    static int k,m,p;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree, order, count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken()); // 노드의 개수
            p = Integer.parseInt(st.nextToken()); // 간선의 수

            inDegree = new int[m+1];
            order = new int[m+1];
            count = new int[m+1];

            adjList = new ArrayList[m+1];
            for(int i=1; i<=m; i++)
            {
                adjList[i] = new ArrayList<>();
            }

            for(int i=0; i<p; i++)
            {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adjList[a].add(b);

                inDegree[b]++;
            }

            topological();

            int max = Integer.MIN_VALUE;

            for(int i=1; i<=m; i++)
            {
                max = Math.max(max, order[i]);
            }
            sb.append(k).append(" ").append(max).append('\n');
        }
        System.out.print(sb);
    }

    static void topological()
    {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=m; i++)
        {
            if(inDegree[i] == 0)
            {
                q.add(i);
                order[i] = 1;
            }
        }

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int next:adjList[cur])
            {
                if(order[cur] > order[next])
                {
                    order[next] = order[cur];
                    count[next] = 1;
                }
                else if(order[cur] == order[next])
                {
                    count[next]++;
                }

                inDegree[next]--;
                if(inDegree[next] == 0)
                {
                    q.add(next);
                    order[next] += count[next] >= 2 ? 1 : 0;
                }
            }
        }
    }
}
