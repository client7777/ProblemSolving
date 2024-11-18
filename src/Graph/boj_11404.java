package Graph;

import java.io.*;
import java.util.StringTokenizer;

public class boj_11404
{
    static int n,m;
    static int[][] ans;
    static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        ans = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i != j)
                {
                    ans[i][j] = MAX;
                }
            }
        }

        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 중복 간선일 경우 더 작은 값으로 초기화, 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            ans[u][v] = Math.min(ans[u][v],w);
        }
        //플로이드 알고리즘
        //중간에 거쳐갈 노드를 가장 바깥 반복문에 위치
        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(ans[i][k] != MAX && ans[k][j] != MAX && ans[i][j] >  ans[i][k] + ans[k][j]) // i -> k로 가는 간선과 k -> j로 가는 간선이 존재하면
                    {
                        ans[i][j] = ans[i][k] + ans[k][j];
                    }
                }
            }
        }

        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(ans[i][j] == MAX)
                {
                    ans[i][j] = 0;
                }
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
