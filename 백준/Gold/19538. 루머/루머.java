import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int[] time, believeRumor;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        time = new int[n+1];
        Arrays.fill(time, -1);

        believeRumor = new int[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            while (true)
            {
                int node = Integer.parseInt(st.nextToken());
                if(node == 0) break;
                graph[i].add(node);
            }
        }

        int rumor = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<rumor; i++)
        {
            int start = Integer.parseInt(st.nextToken());
            q.add(start);
            time[start] = 0;
        }

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int next:graph[cur])
            {
                if(time[next] == -1)
                {
                    believeRumor[next]++;

                    if(believeRumor[next] >= (graph[next].size() + 1) / 2)
                    {
                        time[next] = time[cur] + 1;
                        q.add(next);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(time[i]).append(" ");
        }

        System.out.print(sb);
    }
}
