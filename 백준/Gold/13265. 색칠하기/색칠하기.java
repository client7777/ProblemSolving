import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static ArrayList<Integer>[] graph;
    static int[] color;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i=1; i<=n; i++)
            {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                graph[y].add(x);
            }

            color = new int[n+1];
            boolean flag = true;

            for(int i=1; i<=n; i++)
            {
                if(color[i] == 0)
                {
                    flag = bfs(i);

                    if(!flag) break;
                }
            }

            sb.append(flag ? "possible" : "impossible").append('\n');
        }

        System.out.print(sb);
    }

    static boolean bfs(int start)
    {
        color[start] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for(int next:graph[cur])
            {
                if(color[next] == 0)
                {
                    color[next] = color[cur] * -1;
                    q.add(next);
                }
                else if(color[next] == color[cur]) return false;
            }
        }

        return true;
    }
}
