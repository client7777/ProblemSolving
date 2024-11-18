package Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_21940
{
    static int n,m,k;
    static final int MAX = Integer.MAX_VALUE;
    static int[] city;
    static int[][] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수


        graph = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i != j)
                {
                    graph[i][j] = MAX;
                }
            }
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.min(graph[u][v],w);
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(graph[i][k] != MAX && graph[k][j] != MAX)
                    {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }

        k = Integer.parseInt(br.readLine());
        ArrayList<Integer> city = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++)
        {
            city.add(Integer.parseInt(st.nextToken()));
        }
        int minDist = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++)
        {
            int nowDist = 0;
            for(int j=0; j<city.size(); j++)
            {
                int k = city.get(j);
                //도시 x까지 왕복하는데 걸리는 시간들 중 최댓값을 구하고
                //최대값들 중에서 최소값인 도시 x를 선택
                nowDist = Math.max(nowDist, graph[k][i] + graph[i][k]);
            }
            //현재까지 발견된 최소값보다 더 작은 값이 발견되면
            if(minDist > nowDist)
            {
                //최소값 갱신, 리스트 비우기, 도시 번호 추가
                minDist = nowDist;
                ans.clear();
                ans.add(i);
            }
            //현재까지 발견된 최소값과 현재 거리가 같다면 도시 번호 추가
            else if(minDist == nowDist)
            {
                ans.add(i);
            }
        }
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        for(int node:ans)
        {
            sb.append(node + " ");
        }
        System.out.print(sb);
    }
}
