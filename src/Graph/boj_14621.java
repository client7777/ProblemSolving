package Graph;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_14621
{
    static int n,m;
    static int[] parent;
    static char[] univ;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        univ = new char[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            univ[i] = st.nextToken().charAt(0);
        }
        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.add(new int[]{u,v,d});
        }
        kruskal();
    }
    static void kruskal()
    {
        int used = 0;
        int tot = 0;
        while (!pq.isEmpty())
        {
            if(used > n-1) break;
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int dist = cur[2];
            if(find(from) != find(to))
            {
                if(univ[from] != univ[to])
                {
                    tot += dist;
                    used++;

                    union(from, to);
                }
            }
        }
        //최소 신장 트리를 만들 수 없다면 -1 출력
        System.out.print(used == n-1 ? tot : -1); 
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
    static int find(int x)
    {
        if(x == parent[x]) return x;
       return parent[x] = find(parent[x]);
    }
}
