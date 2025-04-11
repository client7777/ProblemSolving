import java.io.*;
import java.util.*;

class Main
{
    static int n,m;
    static int[] parent, answer;
    static char[][] map;
    static ArrayList<Node> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        answer = new int[n];

        parent = new int[n];
        for(int i=0; i<n; i++)
        {
            parent[i] = i;
        }

        map = new char[n][n];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<n; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(map[i][j] == 'Y') graph.add(new Node(i,j));
            }
        }

        if(graph.size() < m)
        {
            System.out.print(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();

        if(kruskal())
        {
            for(int val : answer)
            {
                sb.append(val).append(" ");
            }
        }
        else
            sb.append(-1);

        System.out.print(sb);
    }

    static boolean kruskal()
    {
        int cnt = 0;

        Queue<Node> next = new LinkedList<>();

        for(Node cur : graph)
        {
            int from = cur.from;
            int to = cur.to;

            if(union(from, to))
            {
                cnt++;
                answer[from]++;
                answer[to]++;
            }
            else next.add(cur);
        }

        if(cnt != n-1) return false;

        for(int i=n-1; i<m; i++)
        {
            Node cur = next.poll();

            answer[cur.from]++;
            answer[cur.to]++;
        }

        return true;
    }

    static boolean union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        parent[rootY] = rootX;

        return true;
    }

    static int find(int x)
    {
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static class Node
    {
        int from;
        int to;

        public Node(int from, int to)
        {
            this.from = from;
            this.to = to;
        }
    }
}