package Graph;

import java.io.*;
import java.util.*;

public class boj_2056
{
    static int n;
    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    static int[] timeTable;
    static int[] endTime;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        inDegree = new int[n+1];
        timeTable = new int[n+1];
        endTime = new int[n+1];

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());

            timeTable[i] = Integer.parseInt(st.nextToken());

            int pre = Integer.parseInt(st.nextToken());
            for(int j=0; j<pre; j++)
            {
                graph[Integer.parseInt(st.nextToken())].add(i);
                inDegree[i]++;
            }
        }
        topologicalSort();
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

                // 작업 next는 여러 선행 작업 중 가장 늦게 끝나는 작업 이후에 시작해야 함.
                // 이 조건을 충족시키기 위해,
                // endTime[next]는 모든 선행 작업의 endTime[cur] + timeTable[next] 값을 비교하여 가장 큰 값을 선택
                endTime[next] = Math.max(endTime[next], endTime[cur] + timeTable[next]);

                if(inDegree[next] == 0)
                {
                    q.add(next);
                }
            }
        }
        int tot = 0;
        // 작업 전체를 완료하는데 걸리는 시간은, 모든 작업의 종료 시간들 중 가장 큰 값
        for(int i=1; i<=n; i++)
        {
            tot = Math.max(tot, endTime[i]);
        }
        System.out.print(tot);
    }
}