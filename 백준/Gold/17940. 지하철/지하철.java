import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static final int INF = 987654321;
    static int[] companyInfo;
    static int[][] map, dist;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        companyInfo = new int[n];
        for(int i=0; i<n; i++)
        {
            //i번 노드가 어떤 회사의 지하철인지 결정
            int company = Integer.parseInt(br.readLine());

            companyInfo[i] = company;
        }

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijkstra();
    }

    static void dijkstra()
    {
        //dist[i][j] = i번 노드까지 j번 환승해서 도달하는데 걸린 최단 시간
        dist = new int[n][n];
        for(int[] row : dist)
        {
            Arrays.fill(row, INF);
        }

        dist[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,companyInfo[0], 0));

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();
            int curNode = cur.node;
            int curDist = cur.dist;
            int curCompany = cur.company;
            int curCnt = cur.cnt;

            if(curNode == m)
            {
                System.out.print(curCnt + " " + curDist);
                return;
            }

            if(curDist > dist[curNode][curCnt]) continue;

            for(int i=0; i<n; i++)
            {
                //다음 노드와 연결이 되어있지 않다면
                if(map[curNode][i] == 0) continue;

                int nextNode = i;
                int nextDist = map[curNode][i];
                int nextCompany = companyInfo[i];
                int nextCnt = (curCompany == nextCompany) ? curCnt : curCnt + 1;

                if(nextCnt >= n) continue;

                if(dist[nextNode][nextCnt] > curDist + nextDist)
                {
                    dist[nextNode][nextCnt] = curDist + nextDist;
                    pq.add(new Node(nextNode, dist[nextNode][nextCnt], nextCompany, nextCnt));
                }
            }
        }
    }

    static class Node implements Comparable<Node>
    {
        int node;
        int dist;
        int company;
        int cnt;

        public Node(int node, int dist, int company, int cnt)
        {
            this.node = node;
            this.dist = dist;
            this.company = company;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o)
        {
            if(this.cnt == o.cnt)
            {
                return Integer.compare(this.dist, o.dist);
            }

            return Integer.compare(this.cnt, o.cnt);
        }
    }
}
