package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_16940
{
    static int n;
    static ArrayList<Integer>[] adjList;
    static int[] order;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=1; i<=n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
            adjList[v].add(u);
        }

        order = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            order[i] = Integer.parseInt(st.nextToken());
        }
        // 이 문제에서 시작정점은 1이므로 시작 정점이 1이 아니라면 0을 출력하고 프로그램 종료
        if(order[0] != 1)
        {
            System.out.print(0);
            return;
        }
        System.out.print(bfs(1) ? 1 : 0);
    }
    static boolean bfs(int start)
    {
        boolean[] visit = new boolean[n+1];
        visit[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int idx = 1; // 함수 호출 전에 order[0]은 이미 검사를 했으므로 인덱스는 1부터 시작

        while (!q.isEmpty())
        {
            int cur = q.poll();
            int childCnt = 0;
            for(int next:adjList[cur])
            {
                if(!visit[next])
                {
                    visit[next] = true;
                    childCnt++;
                }
            }
            for(int i=idx; i<idx + childCnt; i++)
            {
                if(!visit[order[i]]) // 다음 방문할 노드가 방문 가능하지 않다면
                    return false; // 순서가 틀렸으므로 false 반환
                else
                    q.add(order[i]); // 올바른 순서라면 큐에 추가
            }
            idx += childCnt; // 다음 레벨의 시작 인덱스로 이동
        }
        return true;
    }
}
