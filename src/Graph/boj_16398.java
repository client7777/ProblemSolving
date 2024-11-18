package Graph;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_16398
{
    static int n;
    static int[] parent;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for(int i=0; i<n; i++)
        {
            parent[i] = i;
        }
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                int cost = Integer.parseInt(st.nextToken());
                if(i == j) continue;
                pq.add(new int[]{i,j,cost});
            }
        }
        kruskal();
    }
    static void kruskal()
    {
        long tot = 0;
        long usedEdge = 0;
        while (!pq.isEmpty() && usedEdge < n-1)
        {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int cost = cur[2];
            if(find(from) != find(to))
            {
                union(from, to);
                usedEdge++;
                tot += cost;
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
