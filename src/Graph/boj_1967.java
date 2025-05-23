package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1967
{
    static int max = 0;
    static int farNode = -1;
    static boolean[] visit;
    static ArrayList<int[]>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 1)
        {
            //노드가 하나인 경우 트리의 지름은 0
            //예외 처리를 하지 않으면 두 번째 dfs에서 오류 발생
            System.out.print(0);
            return;
        }

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for(int i=0; i<n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[u].add(new int[]{v,cost});
            graph[v].add(new int[]{u,cost});
        }

        visit = new boolean[n+1];
        dfs(1, 0); // 임의의 노드에서 가장 먼 노드 -> 지름의 한쪽 끝

        max = 0;
        dfs(farNode, 0); // 가장 먼 노드까지의 거리 = 트리의 지름

        System.out.print(max);
    }
    
    static void dfs(int node, int sum)
    {
        if(sum > max)
        {
            max = sum;
            farNode = node;
        }
        
        for(int[] next:graph[node])
        {
            int nextNode = next[0];
            int nextDist = next[1];
            
            if(!visit[nextNode])
            {
                visit[node] = true;
                dfs(nextNode, sum + nextDist);
                visit[node] = false;
            }
        }
    }
}
