package Graph;
// MST : 간선의 개수는 노드의 개수 -1, 사이클이 존재해서는 안된다, 모든 정점은 연결되어 있다.
import java.io.*;
import java.util.*;
//크루스칼, 프림
public class boj_1197
{
    static ArrayList<int[]> edges; // 간선의 정보를 저장
    static int[] parent; // 유니온-파인드 부모 노드를 저장할 배열
    static int tot;
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        parent = new int[v+1];
        for(int i=1; i<=v; i++)
        {
            parent[i] = i;
        }
        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{a,b,w});
        }
        edges.sort(Comparator.comparingInt(a -> a[2])); // 가중치를 기준으로 간선을 오름차순 정렬

        for(int[] edge:edges)
        {
            int a = edge[0];
            int b = edge[1];
            int weight = edge[2];

            //유니온 파인드를 통해 mst에 포함될 수 있는 간선인지 확인
            if(union(a,b))
            {
                tot += weight;
            }
        }
        System.out.print(tot);
    }
    static boolean union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY)
        {
            parent[rootY] = rootX;
            return true;
        }
        return false; // 사이클 방지
    }
    static int find(int x)
    {
        if(x != parent[x])
        {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}

/*
    static int tot;
    static ArrayList<int[]>[] adList;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        adList = new ArrayList[v+1];
        visit = new boolean[v+1];
        for(int i=1; i<=v; i++)
        {
            adList[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adList[a].add(new int[]{b,w});
            adList[b].add(new int[]{a,w});
        }
        prim(1);
        System.out.print(tot);
    }
   static void prim(int start)
   {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        visit[start] = true;
        for(int[] edge:adList[start])
        {
            pq.add(edge);
        }
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curWeight = cur[1];

            if(visit[curNode]) continue;
            visit[curNode] = true;
            tot += curWeight;

            for(int[] edge:adList[curNode])
            {
                if(!visit[edge[0]])
                {
                    pq.add(edge);
                }
            }
        }
*/