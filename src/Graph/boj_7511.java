package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_7511
{
    static int[] parent;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=1; t<=test_case; t++)
        {
            sb.append("Scenario ").append(t).append(":").append('\n');
            int n = Integer.parseInt(br.readLine()); // 유저의 수
            int k = Integer.parseInt(br.readLine()); // 친구 관계의 수

            parent = new int[n];
            for(int i=0; i<n; i++)
            {
                parent[i] = i;
            }

            StringTokenizer st;
            for(int i=0; i<k; i++)
            {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a,b);
            }

            int m = Integer.parseInt(br.readLine());
            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                sb.append(find(u) == find(v) ? 1 : 0).append('\n');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static int find(int x)
    {
        if(x == parent[x])
        {
            return x;
        }
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
