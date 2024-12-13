package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1005
{
    static int n,k,w;
    static int[] timeTable, inDegree;
    static long[] endTime;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            inDegree = new int[n+1];

            timeTable = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++)
            {
                timeTable[i] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList[n+1];
            for(int i=1; i<=n; i++)
            {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<k; i++)
            {
                st = new StringTokenizer(br.readLine());
                // x가 y의 선행 노드
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                inDegree[y]++;
            }
            w = Integer.parseInt(br.readLine()); // 목표 건물의 번호

            endTime = new long[n+1];

            topologicalSort();
        }
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
                endTime[i] = timeTable[i];
            }
        }
        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int next:graph[cur])
            {
                inDegree[next]--;

                endTime[next] = Math.max(endTime[next], endTime[cur] + timeTable[next]);

                if(inDegree[next] == 0) q.add(next);
            }
        }
        sb.append(endTime[w]).append('\n');
    }
}
