import java.io.*;
import java.util.*;

public class Main
{
    static int INF = 500 * 10000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n+1][n+1];
        int[][] cost = new int[n+1][n+1];
        int[] dog = new int[n+1];
        Integer[] minDogTime = new Integer[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            dog[i] = Integer.parseInt(st.nextToken());
            minDogTime[i] = i;
        }

        for(int i=1; i<=n; i++)
        {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;

            Arrays.fill(cost[i], INF);
            cost[i][i] = dog[i];
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

        Arrays.sort(minDogTime, 1, n + 1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return dog[o1] - dog[o2];
            }
        });

        int idx = -1;

        for(int k=1; k<=n; k++)
        {
            idx = minDogTime[k];
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    dist[i][j] = Math.min(dist[i][j], dist[i][idx] + dist[idx][j]);

                    int maxTime = Math.max(dog[i], Math.max(dog[idx], dog[j]));

                    cost[i][j] = Math.min(cost[i][j], dist[i][j] + maxTime);
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<q; i++)
        {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            sb.append(cost[s][t] == INF ? -1 : cost[s][t]).append('\n');
        }
        System.out.print(sb);
    }
}
