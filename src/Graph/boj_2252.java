package Graph;
// 사이클이 없는 방향 그래프에서 올바른 위상정렬이 존재함
// 각 학생을 정점으로 세워두고 위상정렬 수행
import java.io.*;
import java.util.*;

public class boj_2252
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
        // inDegree가 0인 노드를 큐에 추가
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
            sb.append(cur).append(' ');
            for(int next:adjList[cur])
            {
                inDegree[next]--;
                if(inDegree[next] == 0)
                {
                    q.add(next);
                }
            }
        }
    }
}
