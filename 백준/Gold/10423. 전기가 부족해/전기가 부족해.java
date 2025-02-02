import java.io.*;
import java.util.*;

public class Main
{
    static int n,m,k;
    static boolean[] plant;
    static ArrayList<int[]>[] edge;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        plant = new boolean[n+1];
        edge = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            edge[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
        {
            int a = Integer.parseInt(st.nextToken()); // 이미 발전소가 설치된 도시
            plant[a] = true;
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge[u].add(new int[]{v,w});
            edge[v].add(new int[]{u,w});
        }
        prim();
    }
    static void prim()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        boolean[] visit = new boolean[n+1];
        int tot =0;

        for(int i=1; i<=n; i++)
        {
            if(plant[i])
            {
                visit[i] = true;
                for(int[] node:edge[i])
                {
                    pq.add(node);
                }
            }
        }
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int to = cur[0];
            int cost = cur[1];
            if(!visit[to])
            {
                visit[to] = true;
                tot += cost;

                for(int[] node:edge[to])
                {
                    pq.add(node);
                }
            }
        }
        System.out.print(tot);
    }
}
