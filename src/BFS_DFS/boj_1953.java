package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1953
{
    static ArrayList<Integer>[] graph;
    static int[] color;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        color = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            for(int j=0; j<m; j++)
            {
                int people = Integer.parseInt(st.nextToken());
                graph[i].add(people);
                graph[people].add(i);
            }
        }

        for(int i=1; i<=n; i++)
        {
            if(color[i] == 0)
            {
                bfs(i);
            }
        }

        ArrayList<Integer> blue = new ArrayList<>();
        ArrayList<Integer> white = new ArrayList<>();

        for(int i=1; i<=n; i++)
        {
            if(color[i] == 1) blue.add(i);
            else if(color[i] == -1) white.add(i);
        }

        Collections.sort(blue);
        Collections.sort(white);

        StringBuilder sb = new StringBuilder();

        sb.append(blue.size()).append('\n');
        for(int val : blue)
        {
            sb.append(val).append(" ");
        }
        sb.append('\n');

        sb.append(white.size()).append('\n');
        for(int val : white)
        {
            sb.append(val).append(" ");
        }
        sb.append('\n');

        System.out.print(sb);
    }

    static void bfs(int start)
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
            }
        }
    }
}
