package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17616
{
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] reverse_graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        reverse_graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
            reverse_graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b); // 정방향
            reverse_graph[b].add(a); //역방향
        }

        StringBuilder sb = new StringBuilder();
        //가능한 최고 등수 -> 나보다 잘하는 학생들과 몇명이나 인접해 있는지 -> 역방향 인접 리스트로 탐색
        //가능한 최저 등수 -> 나보다 못하는 학생들과 몇명이나 인접해 있는지 -> 정방향 인접 리스트로 탐색
        
        sb.append(bfs(n,x,reverse_graph) + 1).append(" ").append(n - bfs(n,x,graph));

        System.out.print(sb);

    }

    static int bfs(int n, int start, ArrayList<Integer>[] graph)
    {
        int res = 0;

        boolean[] visit = new boolean[n+1];
        visit[start] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            Integer curNode = q.poll();

            for(Integer next:graph[curNode])
            {
                if(!visit[next])
                {
                    q.add(next);
                    res++;
                    visit[next] = true;
                }
            }
        }
        return res;
    }
}
