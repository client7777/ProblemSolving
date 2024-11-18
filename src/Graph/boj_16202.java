package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_16202
{
    static int n,m,k;
    static ArrayList<int[]> edge = new ArrayList<>();
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=1; i<=m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = i;
            edge.add(new int[]{u,v,w});
        }
        int[] score = new int[k];
        Collections.sort(edge, Comparator.comparingInt(o->o[2])); // 가중치를 기준으로 오름차순
        for(int i=0; i<k; i++)
        {
            setParent(); // 턴이 시작되면 부모 노드 초기화
            score[i] = kruskal();
            edge.remove(0);
        }
        for(int val:score)
        {
            System.out.print(val + " ");
        }
    }
    static int kruskal()
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
                union(from,to);
                used++;
                tot += cost;
            }
        }
        return used == n-1 ? tot : 0;
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
    static void setParent()
    {
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }
    }
}
