package Graph;

import java.io.*;
import java.util.*;

// 0 = 오르막길 1 = 내리막길
public class boj_13418
{
    static int n,m;
    static int[] parent;
    static ArrayList<int[]> edge = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=0; i<m+1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge.add(new int[]{a,b,c});
        }
        System.out.print(worst() - best());
    }
    static int worst()
    {
        for(int i=0; i<=n; i++)
        {
            parent[i] = i;
        }
        Collections.sort(edge, (o1,o2) -> o1[2]-o2[2]);
        int up = 0;
        int used = 0;
        for(int[] edges:edge)
        {
            if(used >= n) break;
            int from = edges[0];
            int to = edges[1];
            int type = edges[2];
            if(find(from) != find(to))
            {
                union(from, to);
                used++;
                if(type == 0) up++;
            }

        }
        return up * up;
    }
    static int best()
    {
        for(int i=0; i<=n; i++)
        {
            parent[i] = i;
        }
        int up = 0;
        int used = 0;
        Collections.sort(edge, (o1,o2) -> o2[2]-o1[2]);
        for(int[] edges:edge)
        {
            if(used >= n) break;
            int from = edges[0];
            int to = edges[1];
            int type = edges[2];
            if(find(from) != find(to))
            {
                union(from, to);
                used++;
                if(type == 0) up++;
            }
        }
        return up * up;
    }
    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootY != rootX)
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
