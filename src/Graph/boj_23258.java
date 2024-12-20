package Graph;

import java.io.*;
import java.util.*;

public class boj_23258
{
    static int INF = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][][] dist = new int[n+1][n+1][n+1]; //dist[i][j][k] = k 이하의 노드만 거쳐가며 i에서 j로 가는 최단 거리

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                int tmp = Integer.parseInt(st.nextToken());
                if(i == j) continue;
                dist[i][j][0] = ((tmp == 0) ? INF : tmp);
            }
        }

        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for (int j=1; j<=n; j++)
                {
                    //k-1이하의 노드만 거치며 i에서 j로 가는 최단 거리
                    //k-1이하의 집들만 거치며 i에서 k로 가는 최단 거리 + k-1이하의 집들만 거치며 k에서 j로 가는 최단 거리
                    dist[i][j][k] = Math.min(dist[i][j][k-1], dist[i][k][k-1] + dist[k][j][k-1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++)
        {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            //경유 가능한 노드는 1 ~ c-1
            //dist[s][e][c-1]의 값은 1번 부터 c-1번 노드까지의 노드들만 경유 가능한 모든 경로를 고려한 최단 거리
            sb.append(dist[s][e][c-1] == INF ? -1 : dist[s][e][c-1]).append('\n');
        }
        System.out.print(sb);
    }
}
/*
이슬 조건 해석
2^1 + 2^2 + 2^3 + ... + 2^(c-1) < 2^c 가 성립
즉, c미만의 번호를 가진 집만 경유 가능
*/
