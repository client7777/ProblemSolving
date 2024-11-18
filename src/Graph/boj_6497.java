package Graph;

import java.io.*;
import java.util.*;

public class boj_6497
{
    static int[] parent;
    static ArrayList<int[]> edge;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            parent = new int[n];
            for(int i=0; i<n; i++)
            {
                parent[i] = i;
            }

            int worst = 0; // 최악의 경우 모든 간선을 연결해서 돈이 최대로 듦
            edge = new ArrayList<>();
            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                worst += w;
                edge.add(new int[]{u,v,w});
            }
            Collections.sort(edge, Comparator.comparingInt(o->o[2]));
            kruskal(worst);
        }

        System.out.print(sb);
    }
    static void kruskal(int worst)
    {
        int tot = 0;
        for(int[] node:edge)
        {
            int from = node[0];
            int to = node[1];
            int cost = node[2];
            if(find(from)!=find(to))
            {
                union(from, to);
                tot += cost;
            }
        }
        sb.append(worst - tot).append('\n'); // 최악의 비용에서 최소 비용을 빼주면 최대 절약 금액이 나옴
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
