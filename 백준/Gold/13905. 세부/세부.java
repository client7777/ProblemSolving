import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int startNode, endNode;
    static int min = 1_000_000;
    static int[] parent;
    static ArrayList<int[]> edge = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());

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
            edge.add(new int[]{v,u,w});
        }
        Collections.sort(edge, (o1,o2) ->
        {
            return o2[2] - o1[2]; // 무게를 기준으로 내림차순
        });
        System.out.print(kruskal());
    }
   static int kruskal()
   {
       for(int[] node:edge)
       {
           int from = node[0];
           int to = node[1];
           int weight = node[2];
           min = Math.min(min, weight);
           if(find(from) != find(to))
           {
               union(from, to);
               if(find(startNode) == find(endNode))
               {
                   return min;
               }
           }
       }
       return 0;
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
