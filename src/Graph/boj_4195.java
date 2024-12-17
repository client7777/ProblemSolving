package Graph;

import java.io.*;
import java.util.*;

public class boj_4195
{
    static int[] parent, rank;
    static int size = 100000 * 2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=0; t<test_case; t++)
        {
            HashMap<String ,Integer> map = new HashMap<>();
            int f = Integer.parseInt(br.readLine());

            parent = new int[size];
            rank = new int[size];

            for(int i=0; i<size; i++)
            {
                parent[i] = i;
                rank[i] = 1;
            }

            int idx = 0;
            StringTokenizer st;
            for(int i=0; i<f; i++)
            {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if(!map.containsKey(a))
                {
                    map.put(a, idx++);
                }
                if(!map.containsKey(b))
                {
                    map.put(b, idx++);
                }
                sb.append(union(map.get(a), map.get(b))).append('\n');
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
    static int union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY)
        {
            parent[rootY] = rootX;
            rank[rootX] += rank[rootY];
            rank[rootY] = 1;
        }
        return rank[rootX];
    }
}

