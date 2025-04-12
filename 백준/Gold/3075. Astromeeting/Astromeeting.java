import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    static final long INF = Long.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int[] meet = new int[n];
            for(int i=0; i<n; i++)
            {
                int num = Integer.parseInt(br.readLine());
                meet[i] = num;
            }

            long[][] map = new long[p+1][p+1];
            for(int i=1; i<=p; i++)
            {
                for(int j=1; j<=p; j++)
                {
                    if(i == j) continue;

                    map[i][j] = INF;
                }
            }

            for(int i=0; i<q; i++)
            {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long d = Integer.parseInt(st.nextToken());

                map[u][v] = Math.min(map[u][v], d);
                map[v][u] = Math.min(map[v][u], d);
            }

            for(int k=1; k<=p; k++)
            {
                for(int i=1; i<=p; i++)
                {
                    for(int j=1; j<=p; j++)
                    {
                        if(map[i][k] == INF || map[k][j] == INF) continue;
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }

            int meetNode = -1;
            long minDist = INF;

            for(int i=1; i<=p; i++)
            {
                long dist = 0;
                boolean flag = true;

                for(int val : meet)
                {
                    if(map[i][val] == INF)
                    {
                        //한명이라도 중간 지점과 연결이 되어있지 않으면 후보에서 탈락
                        flag = false;
                        break;
                    }

                    dist += map[i][val] * map[i][val];
                }

                if(flag && minDist > dist)
                {
                    meetNode = i;
                    minDist = dist;
                }
            }

            sb.append(meetNode).append(" ").append(minDist);
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
