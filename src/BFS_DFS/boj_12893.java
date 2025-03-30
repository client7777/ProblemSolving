package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_12893
{
    static ArrayList<Integer>[] graph;
    static int[] color;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        color = new int[n+1];

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        boolean flag = true;

        for(int i=1; i<=n; i++)
        {
            if(color[i] == 0)
            {
                flag = bfs(i);

                if(!flag) break;
            }
        }

        System.out.print(flag ? 1 : 0);
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
                else if(color[cur] == color[next]) return false;
            }
        }
        return true;
    }
}
