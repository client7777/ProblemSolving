package Graph;

import java.io.*;
import java.util.*;

public class boj_16202
{
    static int n,m,k;
    static int[] parent, score;
    static ArrayList<Node> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        score = new int[k];

        for(int i=1; i<=m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.add(new Node(from, to, i));
        }

        Collections.sort(graph, Comparator.comparingInt(o -> o.weight));
        
        for(int i=0; i<k; i++)
        {
            setParent(); // 새로운 턴이 시작되기 전에 루트 노드 재구성
            score[i] = kruskal();
            graph.remove(0); // 해당 턴에서 구한 MST에서 가장 가중치가 작은 간선 하나를 제거
        }

        StringBuilder sb = new StringBuilder();
        for(int val : score)
        {
            sb.append(val).append(" ");
        }

        System.out.print(sb);
    }

    static int kruskal()
    {
        int used = 0;
        int totalCost = 0;

        for(Node node : graph)
        {
            if(used == n-1) break;

            int from = node.from;
            int to = node.to;
            int weight = node.weight;

            if(find(from) != find(to))
            {
                union(from, to);
                used++;
                totalCost += weight;
            }
        }

        return used == n-1 ? totalCost : 0;
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

    static class Node
    {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
