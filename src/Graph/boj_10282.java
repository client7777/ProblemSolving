package Graph;

import java.io.*;
import java.util.*;

public class boj_10282
{
    static int n,d,c;
    static ArrayList<int[]>[] adjList;
    static int[] dist;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());


        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 총 노드의 개수
            d = Integer.parseInt(st.nextToken()); // 간선의 개수
            c = Integer.parseInt(st.nextToken()); // 시작 노드 번호

            adjList = new ArrayList[n+1];
            for(int i=1; i<=n; i++)
            {
                adjList[i] = new ArrayList<>();
            }

            dist = new int[n+1];

            for(int i=0; i<d; i++)
            {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                adjList[b].add(new int[]{a,s});
            }
            dijkstra(c);
        }
        System.out.print(sb);
    }
    static void dijkstra(int start)
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0});

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        int cnt = 0;
        int lastTime = 0;

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curTime = cur[1];

            if (curTime > dist[curNode]) continue;

            cnt++; // 감염된 컴퓨터 수 증가
            lastTime = curTime; // 마지막으로 감염된 컴퓨터의 시간 업데이트

            for (int[] next : adjList[curNode])
            {
                int nextCom = next[0];
                int nextTime = next[1];

                if (dist[nextCom] > dist[curNode] + nextTime)
                {
                    dist[nextCom] = dist[curNode] + nextTime;
                    pq.add(new int[]{nextCom, dist[nextCom]});
                }
            }
        }

        sb.append(cnt).append(" ").append(lastTime).append('\n'); // 결과 출력
    }
}

/*

static void dijkstra(int start)
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{start, 0});

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curTime = cur[1];

            if(curTime > dist[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextCom = next[0];
                int nextTime = next[1];

                if(dist[nextCom] > dist[curNode] + nextTime)
                {
                    dist[nextCom] = dist[curNode] + nextTime;
                    pq.add(new int []{nextCom, dist[nextCom]});
                }
            }
        }
        int cnt = 0;
        int maxTime = 0;
        for(int i=1; i<=n; i++)
        {
            if(dist[i] != Integer.MAX_VALUE)
            {
                cnt++;
                maxTime = Math.max(maxTime, dist[i]);
            }
        }
        sb.append(cnt + " " + maxTime).append('\n');
    }
*/