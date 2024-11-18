package Graph;

import java.io.*;
import java.util.*;

public class boj_1944
{
    static int n,m;
    static int nodeNum = 2;
    static char[][] map;
    static int[][] state;
    static int[] parent;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        state = new int[n][n];
        map = new char[n][n];
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S' || map[i][j] == 'K')
                {
                    state[i][j] = nodeNum++;
                }
            }
        }
        parent = new int[nodeNum];
        for(int i=2; i<nodeNum; i++)
        {
            parent[i] = i;
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                // 배열의 값이 S,K이면 즉, 노드이면 탐색 시작점이 될 수 있음
                if(map[i][j] == 'S' || map[i][j] == 'K')
                {
                    bfs(i,j);
                }
            }
        }
        System.out.print(kruskal());
    }
    static int kruskal()
    {
        int tot = 0;
        int used = 0;
        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int cost = cur[2];
            if(find(from) != find(to))
            {
                tot += cost;
                union(from, to);
                used ++;
            }
        }
        return used == m ? tot : -1; // 열쇠 m개와 간선을 연결했다면 tot 아니라면 -1 리턴
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
    static void bfs(int startX, int startY)
    {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][n];
        q.add(new int[]{startX, startY,0,state[startX][startY]});
        visit[startX][startY] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            int fromNode = cur[3];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '1') continue;
                q.add(new int[]{nX,nY,curDist + 1,fromNode});
                visit[nX][nY] = true;
                if(map[nX][nY] == 'S' || map[nX][nY] == 'K') // 다른 노드를 발견했다면
                {
                    pq.add(new int[]{fromNode, state[nX][nY], curDist + 1}); // 간선 정보 저장
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
