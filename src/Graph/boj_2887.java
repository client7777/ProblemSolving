package Graph;
// 노드의 개수 파악 확실히
import java.io.*;
import java.util.*;

public class boj_2887
{
    static int n;
    static int[] parent;
    static int[][] planet;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }
        planet = new int[n+1][4];
        for(int i=1; i<=n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planet[i][0] = i;
            planet[i][1] = Integer.parseInt(st.nextToken());
            planet[i][2] = Integer.parseInt(st.nextToken());
            planet[i][3] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=3; i++)
        {
            int axis = i;
            Arrays.sort(planet,1,n+1,Comparator.comparingInt(o->o[axis]));
            for(int j=2; j<=n; j++)
            {
                int dist = Math.abs(planet[j][axis] - planet[j-1][axis]);
                pq.add(new int[]{planet[j][0],planet[j-1][0],dist});
                pq.add(new int[]{planet[j-1][0],planet[j][0],dist});
            }
        }
        kruskal();
    }
    static void kruskal()
    {
        int tot = 0;
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int dist = cur[2];
            if(find(from) != find(to))
            {
                union(from, to);
                tot += dist;
            }
        }
        System.out.print(tot);
    }
    static int find(int x)
    {
        if(x == parent[x]) return x;
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
}
