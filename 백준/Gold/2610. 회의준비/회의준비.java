import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static final int INF = Integer.MAX_VALUE / 2;
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] map = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }

        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u][v] = map[v][u] = 1;
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        parent = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;

                if(map[i][j] != INF)
                {
                    union(i,j);
                }
            }
        }

        HashMap<Integer, ArrayList<Integer>> group = new HashMap<>();
        for(int i=1; i<=n; i++)
        {
            int root = find(i);
            group.putIfAbsent(root, new ArrayList<>());
            group.get(root).add(i);
        }

        ArrayList<Integer> answer = new ArrayList<>();

        for(ArrayList<Integer> list : group.values())
        {
            int minMaxDist = INF;
            int idx = -1;

            for(int me : list)
            {
                int maxDist = 0;

                for(int other : list)
                {
                    if(me == other) continue;
                    maxDist = Math.max(maxDist, map[me][other]);
                }

                if(minMaxDist > maxDist)
                {
                    minMaxDist = maxDist;
                    idx = me;
                }
            }

            answer.add(idx);
        }

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');

        for(int val : answer)
        {
            sb.append(val).append('\n');
        }

        System.out.print(sb);
    }

    static int find(int x)
    {
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }
}
