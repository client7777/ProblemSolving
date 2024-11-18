package Graph;
// DFS와 BFS
import java.io.*;
import java.util.*;

public class boj_1260
{
    static int n,m,v;
    static int[][] matrix;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        matrix = new int[n+1][n+1];
        visit = new boolean[n+1];

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }
        dfs(v);
        Arrays.fill(visit,false);
        sb.append('\n');
        bfs(v);
        System.out.print(sb);
    }

    static void dfs(int v)
    {
        visit[v] = true;
        sb.append(v).append(" ");
        for(int i=1; i<=n; i++)
        {
            if(matrix[v][i] == 1 && !visit[i])
            {
                dfs(i);
            }
        }
    }

    static void bfs(int v)
    {
       Queue<Integer> q = new LinkedList<>();
       q.add(v);
       visit[v] = true;
       while (!q.isEmpty())
       {
           int cur = q.poll();
           sb.append(cur).append(" ");
           for(int i=1; i<=n; i++)
           {
               if(matrix[cur][i] == 1 && !visit[i])
               {
                   q.add(i);
                   visit[i] = true;
               }
           }
       }
    }
}
