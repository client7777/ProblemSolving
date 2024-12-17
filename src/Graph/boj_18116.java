package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_18116
{
    static final int SIZE = 10000001;
    static int[] parent, rank;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[SIZE];
        rank = new int[SIZE];

        for(int i=0; i<SIZE; i++)
        {
            parent[i] = i;
            rank[i] = 1;
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if(command.equals("I"))
            {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }
            else if(command.equals("Q"))
            {
                int c = Integer.parseInt(st.nextToken());
                sb.append(rank[find(c)]).append('\n');
            }
        }
        System.out.print(sb);
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
            parent[rootY] = rootX;
            rank[rootX] += rank[rootY];
            rank[rootY] = 1;
        }
    }
}
