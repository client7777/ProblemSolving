package Graph;
// 출발지부터 목적지까지 최대신장 트리를 만들면서 출발지와 목적지가 같은 집합이 되는 순간의 지나온 가중치 중 가장 작은 값을 선택
import java.io.*;
import java.util.*;

public class boj_13905
{
    static int n,m;
    static int startNode, endNode;
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

           if(find(from) != find(to))
           {
               union(from, to);
               if(find(startNode) == find(endNode))
               {
                   return weight;
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
