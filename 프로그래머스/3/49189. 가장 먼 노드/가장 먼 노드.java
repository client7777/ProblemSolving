import java.util.*;

class Solution 
{
    public int solution(int n, int[][] edge)
    {
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int[] row:edge)
        {
            int from = row[0];
            int to = row[1];

            graph[from].add(to);
            graph[to].add(from);
        }

        int[] dist = solve(n, 1, graph);

        int max = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, dist[i]);
        }

        int cnt = 0;
        for(int i=1; i<=n; i++)
        {
            if(dist[i] == max)  cnt++;
        }

        return cnt;
    }

    static int[] solve(int n, int start, ArrayList<Integer>[] graph)
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int curNode = q.poll();

            for(int nextNode:graph[curNode])
            {
                if(dist[nextNode] == -1)
                {
                    dist[nextNode] = dist[curNode] + 1;
                    q.add(nextNode);
                }
            }
        }
        return dist;
    }
}