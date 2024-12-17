package Graph;

import java.io.*;

public class boj_10775
{
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());

        parent = new int[g+1];
        for(int i=0; i<=g; i++)
        {
            parent[i] = i;
        }

        int p = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i=0; i<p; i++)
        {
            int cur = Integer.parseInt(br.readLine());
            int curRoot = find(cur);
            if(curRoot == 0) break;
            cnt++;
            union(curRoot, curRoot-1);
        }
        System.out.print(cnt);
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
            if(rootY > rootX)
            {
                parent[rootY] = rootX;
            }
            else
                parent[rootX] = rootY;
        }
    }
}
