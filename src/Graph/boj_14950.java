package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_14950
{
    static int n,m,t;
    static int[] parent;
    static ArrayList<int[]> edge = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

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
            int w = Integer.parseInt(st.nextToken());
            edge.add(new int[]{u,v,w});
        }
        Collections.sort(edge, Comparator.comparingInt(o->o[2]));
        kruskal();
    }
    static void kruskal()
    {
        int used = 0;
        int tot = 0;
        for(int[] node:edge)
        {
            if(used == n-1) break;
            int from = node[0];
            int to = node[1];
            int cost = node[2];
            if(find(from) != find(to))
            {
                union(from, to);
                tot = tot + cost + (t * used);
                used++;
            }
        }
        System.out.print(tot);
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
