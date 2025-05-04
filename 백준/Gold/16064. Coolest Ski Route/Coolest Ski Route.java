import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int[] d; // d[i] = i번 노드까지 도달했을 때 얻을 수 있는 최대 점수
    static int[] inDegree; // 진입차수
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d = new int[n+1];
        inDegree = new int[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new Node(t,c));
            inDegree[t]++;
        }

        topological();

        int answer = 0;
        for(int i=1; i<=n; i++)
        {
            answer = Math.max(answer, d[i]);
        }

        System.out.print(answer);
    }

    static void topological()
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

            for(Node next : graph[cur])
            {
                int nextNode = next.node;
                int nextDist = next.dist;

                d[nextNode] = Math.max(d[nextNode], d[cur] + nextDist);

                if(--inDegree[nextNode] == 0)
                    q.add(nextNode);
            }
        }
    }

    static class Node
    {
        int node;
        int dist;

        public Node(int node, int dist)
        {
            this.node = node;
            this.dist = dist;
        }
    }
}
