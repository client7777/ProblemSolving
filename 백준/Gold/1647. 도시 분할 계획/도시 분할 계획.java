import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m;
    static ArrayList<int[]>[] adList;
    static boolean[] visit;
    static int tot = 0;
    static int maxCost = 1;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n+1];
        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adList[start].add(new int[]{end, weight});
            adList[end].add(new int[]{start, weight});
        }
        prim(1);
        System.out.print(tot - maxCost);
    }
    static void prim(int x)
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        visit[x] = true;
        for(int[] start:adList[x])
        {
            pq.add(start);
        }
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if(visit[curNode]) continue;
            visit[curNode] = true;
            tot += curCost;
            // 최소신장 트리를 이루고 있는 간선 중에서 비용이 가장 큰 간선을 끊어서 하나의 트리를 두개로 분리
            maxCost = Math.max(maxCost, curCost); 

            for(int[] edge:adList[curNode])
            {
                if(!visit[edge[0]])
                {
                    pq.add(edge);
                }
            }
        }
    }
}
