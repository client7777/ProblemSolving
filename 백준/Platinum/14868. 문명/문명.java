import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,k,cnt;
    static int[][] map;
    static int[] parent;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<Node> q1 = new LinkedList<>();
    static Queue<Node> q2 = new LinkedList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        parent = new int[k+1];

        for(int i=1; i<=k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            q1.add(new Node(x,y));
            parent[i] = i;
            map[x][y] = i;
        }

        int day = 0;
        cnt = k;

        //초기상태에서 문명 병합 -> 모든 문명이 하나로 병합되었다면 루프 탈출 아니라면 문명 전파
        while (true)
        {
            merge();
            if(cnt == 1) break;
            spread();
            day++;
        }

        System.out.print(day);
    }

    static void merge()
    {
        while (!q1.isEmpty())
        {
            Node cur = q1.poll();
            int curX = cur.x;
            int curY = cur.y;

            q2.add(cur);

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;

                if(map[nX][nY] != 0)
                {
                    if(find(map[curX][curY]) != find(map[nX][nY]))
                    {
                        union(map[curX][curY],map[nX][nY]);
                        cnt--;
                    }
                }
            }
        }
    }

    static void spread()
    {
        while (!q2.isEmpty())
        {
            Node cur = q2.poll();
            int curX = cur.x;
            int curY = cur.y;

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY)) continue;

                if(map[nX][nY] == 0)
                {
                    map[nX][nY] = map[curX][curY];
                    q1.add(new Node(nX,nY));
                }
            }
        }
    }

    static int find(int x)
    {
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x,int y)
    {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY)
        {
            parent[rootY] = rootX;
        }
    }
    static boolean OOB(int x, int y)
    {
        return x < 1 || y < 1 || x > n || y > n;
    }

    static class Node
    {
        int x;
        int y;

        public Node(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
