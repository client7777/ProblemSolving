package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16958
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Pos[] pos = new Pos[n+1];
        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(s,x,y);
        }

        int[][] dist = new int[n+1][n+1];
        for(int i=1; i<=n; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(i == j) continue;

                //각 노드 사이의 거리를 맨해튼 거리로 초기화
                int gap = Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y);

                dist[i][j] = gap;

                //i번 노드, j번 노드가 특별한 도시이면 텔레포트 거리와 맨해튼 거리 중 최단 거리를 택함
                if(pos[i].s == 1 && pos[j].s == 1)
                {
                    dist[i][j] = Math.min(gap, t);
                }
            }
        }
        
        //플로이드 알로리즘
        for(int k=1; k<=n; k++)
        {
            for(int i=1; i<=n; i++)
            {
                for(int j=1; j<=n; j++)
                {
                    if(i == j)  continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++)
        {
            //쿼리
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(dist[a][b]).append('\n');
        }

        System.out.print(sb);
    }

    static class Pos
    {
        int s;
        int x;
        int y;

        public Pos(int s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
    }
}
