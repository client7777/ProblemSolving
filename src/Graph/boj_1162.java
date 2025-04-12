package Graph;

import java.io.*;
import java.util.*;

public class boj_1162
{
    static int n,m,k;
    static ArrayList<int[]>[] graph;
    static long[][] dist;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,d,k});
            graph[v].add(new int[]{u,d,k});
        }
        dist = new long[k+1][n+1];
        for(int i=0; i<=k; i++)
        {
            Arrays.fill(dist[i], INF);
        }

        dijkstra();
    }
    static void dijkstra()
    {
        dist[0][1] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{1,0,0});

        while (!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curDist = cur[1];
            int curRoad = (int)cur[2];

            if(curNode == n)
            {
                System.out.print(curDist);
                return;
            }
            if(curDist > dist[curRoad][curNode]) continue;

            for(int[] next:graph[curNode])
            {
                int nextNode = next[0];
                long nextDist = next[1];

                if(dist[curRoad][nextNode] > dist[curRoad][curNode] + nextDist)
                {
                    dist[curRoad][nextNode] = dist[curRoad][curNode] + nextDist;
                    pq.add(new long[]{nextNode, dist[curRoad][nextNode], curRoad});
                }

                if(curRoad < k && dist[curRoad+1][nextNode] > dist[curRoad][curNode])
                {
                    dist[curRoad+1][nextNode] = dist[curRoad][curNode];
                    pq.add(new long[]{nextNode, dist[curRoad][curNode], curRoad+1});
                }
            }
        }
    }
}
/*
도시 수가 10,000이고 비용이 백만 이하이기 때문에 int 형 변수 범위를 벗어날 수 있으므로 
최단 거리 배열은 long 타입으로 선언
최단 거리 테이블을 포장 횟수에 따라 구분해서 기록
dist[포장 횟수][노드 번호]
*/