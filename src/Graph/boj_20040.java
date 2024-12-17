package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_20040
{
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for(int i=0; i<n; i++)
        {
            parent[i] = i;
        }

        int turn = 1;

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(union(x,y)) turn++;
            else break;
        }

        System.out.print(turn > m ? 0 : turn);
    }
    static int find(int x)
    {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        parent[rootY] = rootX;

        return true;
    }
}
