import java.io.*;
import java.util.*;

public class Main
{
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
   }
}
