package Graph;

import java.io.*;
import java.util.*;

public class boj_17835
{
    static int n,m,k;
    static long maxDist = Long.MIN_VALUE;
    static int minCity = Integer.MAX_VALUE;
    static ArrayList<int[]>[] adjList;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 노드의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수
        k = Integer.parseInt(st.nextToken()); // 면접장의 개수

        adjList = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[v].add(new int[]{u,c}); // 역방향 저장
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i < k; i++)
        {
            list.add(Integer.parseInt(st.nextToken()));
        }

        dijkstra();
        StringBuilder sb = new StringBuilder();
        sb.append(minCity).append('\n').append(maxDist);
        System.out.print(sb);
    }
    static void dijkstra()
    {
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        for(int start : list)
        {
            dist[start] = 0;
            pq.add(new long[]{start, 0});
        }

        while (!pq.isEmpty())
        {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];
            long curCost = cur[1];

            if(curCost > dist[curNode]) continue;

            for(int[] next:adjList[curNode])
            {
                int nextNode = next[0];
                int nextCost = next[1];

                if(dist[nextNode] > dist[curNode] + nextCost)
                {
                    dist[nextNode] = dist[curNode] + nextCost;
                    pq.add(new long[]{nextNode, dist[nextNode]});
                }
            }
        }
        for (int i = 1; i <= n; i++)
        {
            if (dist[i] != Long.MAX_VALUE)
            {
                if (dist[i] > maxDist)
                {
                    maxDist = dist[i];
                    minCity = i;
                } else if (dist[i] == maxDist && i < minCity)
                {
                    minCity = i;
                }
            }
        }
    }
}

/*
static int n,m,k;
    static long maxDist = Long.MIN_VALUE;
    static int minCity = Integer.MAX_VALUE;
    static ArrayList<Edge>[] adjList;
    static ArrayList<Integer> list = new ArrayList<>();

    static class Edge
    {
        int node;
        int cost;

        Edge(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }
    }

    static class State implements Comparable<State>
    {
        int node;
        long dist;

        State(int node, long dist)
        {
            this.node = node;
            this.dist = dist;
        }
        @Override
        public int compareTo(State o)
        {
            return Long.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 노드의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수
        k = Integer.parseInt(st.nextToken()); // 면접장의 개수

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 면접장에서 모든 도시로의 최단 거리를 계산하기 위해서 역방향 저장
            // 한번의 다익스트라 수행을 통해서 문제 해결 가능

            adjList[v].add(new Edge(u,c));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i < k; i++)
        {
            list.add(Integer.parseInt(st.nextToken()));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        sb.append(minCity).append('\n').append(maxDist);
        System.out.print(sb);
    }
    static void dijkstra()
    {
        long[] dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        for(int start:list)
        {
            dist[start] = 0;
            pq.add(new State(start, 0));
        }

        while (!pq.isEmpty())
        {
            State cur = pq.poll();

            if(cur.dist > dist[cur.node]) continue;

            for(Edge next:adjList[cur.node])
            {
                if(dist[next.node] > dist[cur.node] + next.cost)
                {
                    dist[next.node] = dist[cur.node] + next.cost;
                    pq.add(new State(next.node, dist[next.node]));
                }
            }
        }
        for(int i=1; i<=n; i++)
        {
            if(dist[i] != Long.MAX_VALUE)
            {
                if(dist[i] > maxDist)
                {
                    maxDist = dist[i];
                    minCity = i;
                }
                else if(dist[i] == maxDist && i < minCity)
                {
                    minCity = i;
                }
            }
        }
    }
*/
