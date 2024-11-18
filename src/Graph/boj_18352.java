package Graph;
// 시간복잡도 = O(N+M), 정점의 개수 + 간선의 개수
import java.io.*;
import java.util.*;

public class boj_18352
{
    static int n,m,k,x; // 정점의 개수, 간선의 개수, 인접 리스트, 출발 노드
    static ArrayList<Integer>[] adList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adList[a].add(b); // a -> b 방향으로 간선이 이어진 노드, 단방향
        }
        bfs(x);
        System.out.print(sb);
    }
    static void bfs(int start)
    {
        int[] dist = new int[n+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int curVertex = q.poll(); // 현재 노드의 좌표
            for(int next:adList[curVertex])
            {
                if(dist[next] == INF)
                {
                    dist[next] = dist[curVertex] + 1; // 현재 정점 거리 + 1
                    q.add(next);
                }
            }
        }
        //최단 거리가 k인 노드를 찾지 못했는지 체크
        boolean isFound = false;
        for(int i=1; i<=n; i++)
        {
            if(dist[i] == k)
            {
                sb.append(i).append('\n');
                isFound = true; // 거리가 k인 노드가 하나라도 존재한다면
            }
        }
        if(!isFound)
        {
            sb.append(-1);
        }
    }
}
