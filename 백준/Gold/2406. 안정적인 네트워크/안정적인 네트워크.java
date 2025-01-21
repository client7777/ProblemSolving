import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }
        
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //x,y는 직접 연결
            union(x,y);
        }

        int[][] cost = new int[n+1][n+1];

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=2; i<=n; i++)
        {
            for(int j=i+1; j<=n; j++)
            {
                pq.add(new Edge(i,j,cost[i][j]));
            }
        }

        kruskal();
    }

    static void kruskal()
    {
        int min = 0;
        int cnt = 0;
        ArrayList<int[]> ans = new ArrayList<>();
        while (!pq.isEmpty())
        {
            Edge cur = pq.poll();
            int from = cur.from;
            int to = cur.to;
            int cost = cur.cost;

            if(find(from) != find(to))
            {
                union(from, to);
                min += cost;
                cnt++;
                ans.add(new int[]{from ,to});
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(cnt).append('\n');
        for(int[] val:ans)
        {
            sb.append(val[0]).append(" ").append(val[1]).append('\n');
        }

        System.out.print(sb);

    }

    static int find(int x)
    {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }

    private static class Edge implements Comparable<Edge>
    {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o)
        {
            return this.cost - o.cost;
        }
    }
}
