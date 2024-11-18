package BFS_DFS;
// O(V+E)logV
import java.io.*;
import java.util.*;

public class boj_15971
{
    static int n, robot1, robot2;
    static ArrayList<int[]>[] adjList;
    static int max = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 방의 개수
        robot1 = Integer.parseInt(st.nextToken());
        robot2 = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adjList[u].add(new int[]{v,dist});
            adjList[v].add(new int[]{u,dist});
        }

        bfs();
    }
    static void bfs()
    {
        boolean[] visit = new boolean[n+1];
        visit[robot1] = true;
        // 가중치를 기준으로 오름차수
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{robot1, 0, 0}); // 시작 좌표, 가중치, 최대값

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int totWeight = cur[1]; // 경로에 있는 모든 가중치를 더함
            int curMax = cur[2];

            if(curNode == robot2)
            {
                System.out.print(totWeight - curMax);
                return;
            }
            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextWeight = next[1];

                if(!visit[nextNode])
                {
                    visit[nextNode] = true;
                    pq.add(new int[]{nextNode,totWeight + nextWeight, Math.max(curMax, nextWeight)});
                }
            }
        }
    }
}
