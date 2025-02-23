import java.util.*;

class Solution 
{
    public int solution(int n, int[][] edge)
    {
        ArrayList<int[]>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int[] row:edge)
        {
            int from = row[0];
            int to = row[1];

            graph[from].add(new int[]{to, 1});
            graph[to].add(new int[]{from ,1});
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

    static int[] solve(int n, int start, ArrayList<int[]>[] graph)
    {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start ,0});

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                int nextDist = next[1] + 1;

                if(dist[nextNode] > curDist + nextDist)
                {
                    dist[nextNode] = curDist + nextDist;
                    pq.add(new int[]{nextNode, curDist + nextDist});
                }
            }
        }
        return dist;
    }
}