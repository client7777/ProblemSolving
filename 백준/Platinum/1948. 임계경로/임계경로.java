import java.io.*;
import java.util.*;

public class Main
{
    static int n, m, start, end;
    static ArrayList<int[]>[] adjList, reverse_adjList;
    static int[] inDegree, dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 도시의 개수
        m = Integer.parseInt(br.readLine()); // 도로의 개수

        inDegree = new int[n+1];
        dist = new int[n+1]; // dist[i] = i번째 도로에 도달하는 데에 걸리는 최대 시간

        adjList = new ArrayList[n+1];
        reverse_adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
            reverse_adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 출발
            int b = Integer.parseInt(st.nextToken()); // 도착
            int c = Integer.parseInt(st.nextToken()); // 비용

            adjList[a].add(new int[]{b,c}); // a는 b의 선행 노드
            reverse_adjList[b].add(new int[]{a,c}); // 역추적을 위한 역방향 인접 리스트
            inDegree[b]++;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        topologicalSort();

        System.out.println(dist[end]);

        System.out.print(reverse());

    }

    static void topologicalSort()
    {
        Arrays.fill(dist, Integer.MIN_VALUE);
        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<=n; i++)
        {
            if(inDegree[i] == 0)
            {
                q.add(i);
                dist[i] = 0;
            }
        }

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int[] next:adjList[cur])
            {
                int nextNode = next[0];
                int nextDist = next[1];
                
                // 최장 거리 갱신
                if(dist[nextNode] < nextDist + dist[cur])
                {
                    dist[nextNode] = nextDist + dist[cur];
                }
                
                // 진입차수 감소, 감소시킨 진입 차수가 0이라면 큐에 추가
                inDegree[nextNode]--;
                if(inDegree[nextNode] == 0)
                    q.add(nextNode);
            }
        }
    }

    static int reverse()
    {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[n+1];
        visit[end] = true;
        q.add(end);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int[] next:reverse_adjList[cur])
            {
                int nextNode = next[0];
                int nextDist = next[1];

                if(dist[cur] == dist[nextNode] + nextDist)
                {
                    cnt++;
                    
                    if(!visit[nextNode])
                    {
                        visit[nextNode] = true;
                        q.add(nextNode);
                    }
                }
            }
        }
        return cnt;
    }
}
