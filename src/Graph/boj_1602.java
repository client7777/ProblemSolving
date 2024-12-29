package Graph;

import java.io.*;
import java.util.*;

public class boj_1602
{
    static int INF = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] dog = new int[n+1];
        int[][] dist = new int[n+1][n+1];
        int[][] cost = new int[n+1][n+1];
        ArrayList<Integer> idx = new ArrayList<>();

        for(int i=1; i<=n; i++)
        {
            Arrays.fill(dist[i], INF);
            Arrays.fill(cost[i], INF);
            dist[i][i] = 0;
            cost[i][i] = 0;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            dog[i] = Integer.parseInt(st.nextToken());
            idx.add(i);
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            dist[u][v] = dist[v][u] = d;
            cost[u][v] = cost[v][u] = d + Math.max(dog[u], dog[v]);
        }

        idx.sort(Comparator.comparingInt(o->dog[o]));

        for(int k=1; k<=n; k++)
        {
            int minTimeIdx = idx.get(k-1);
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    dist[i][j] = Math.min(dist[i][j], dist[i][minTimeIdx] + dist[minTimeIdx][j]);
                    int maxTime = Math.max(dog[i], Math.max(dog[minTimeIdx], dog[j]));
                    cost[i][j] = Math.min(cost[i][j], dist[i][j] + maxTime);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (q-- > 0)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            sb.append(cost[s][t] == INF ? -1 : cost[s][t]).append('\n');
        }
        System.out.print(sb);
    }
}
