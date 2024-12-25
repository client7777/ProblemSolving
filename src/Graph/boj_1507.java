package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1507
{
    static int[][] graph;
    static int[][] copy;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        graph = new int[n][n];
        copy = new int[n][n];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                graph[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = graph[i][j];
            }
        }

        for(int k=0; k<n; k++)
        {
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<n; j++)
                {
                    //자기 자신으로 가는 경로는 의미 없음
                    //경유지가 출발지와 같으면 사실상 경유지를 거치지 않고 목적지로 가는 경우와 같음
                    //경유지가 목적지와 같다면 출발지에서 경유지를 거치지 않고 목적지로 가는 경우와 같음
                    if(i == j || i == k || j == k) continue;
                    if(graph[i][j] > graph[i][k] + graph[k][j])
                    {
                        System.out.print(-1);
                        return;
                    }
                    //경유지를 거쳐서 가는 거리와 그렇지 않은 거리가 같다면 직접 연결 간선이 아니다.
                    //필요없는 간선 제거
                    if(graph[i][j] == graph[i][k] + graph[k][j])
                        copy[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                ans += copy[i][j];
            }
        }
        //양방향 간선이므로 누적값을 2로 나눔
        System.out.print(ans/2);
    }
}
