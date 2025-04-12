import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Info[] infos = new Info[n];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            infos[i] = new Info(m,v);
        }

        Arrays.sort(infos, (a,b) ->
        {
            if(a.m == b.m) return Integer.compare(b.v, a.v);
            return Integer.compare(a.m, b.m);
        });

        int[] bag = new int[k];
        for(int i=0; i<k; i++)
        {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o));
        long ans = 0;

        int idx = 0;
        for(int weight : bag)
        {
            while (idx < n && infos[idx].m <= weight)
            {
                pq.add(infos[idx].v);
                idx++;
            }

            if(!pq.isEmpty())
            {
                ans += pq.poll();
            }
        }

        System.out.print(ans);
    }

    static class Info
    {
        int m;
        int v;

        public Info(int m, int v)
        {
            this.m = m;
            this.v = v;
        }
    }
}
