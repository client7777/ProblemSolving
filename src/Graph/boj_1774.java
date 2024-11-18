package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_1774
{
    static int n,m;
    static int[] parent;
    static ArrayList<double[]> edge = new ArrayList<>();
    static int[] x,y; // 노드의 좌표를 저장
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
        x = new int[n+1];
        y = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }
        for(int i=1; i<=n; i++)
        {
            for(int j=i+1; j<=n; j++)
            {
                double distance = Math.sqrt(Math.pow(x[i]-x[j],2) + Math.pow(y[i] - y[j],2));
                edge.add(new double[]{i,j,distance});
            }
        }
        Collections.sort(edge,Comparator.comparingDouble(o->o[2]));
        kruskal();
    }
    static void kruskal()
    {
        double tot = 0.0;
        int used = 1;
        for(double[] node:edge)
        {
            if(used > n-1) break;
            int from = (int)node[0];
            int to = (int)node[1];
            double dist = node[2];
            if(find(from) != find(to))
            {
                union(from, to);
                tot += dist;
                used++;
            }
        }
        System.out.printf("%.2f",tot);
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
