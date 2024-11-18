package Graph;
//다리만들기 2, MST, prim, union-find
import java.io.*;
import java.util.*;

public class boj_17472
{
    static int n,m;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int num = 1, landCnt = 0;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
    static int[] parent;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        marking();
        buildBridge();
        parent = new int[landCnt+1];
        for(int i=1; i<landCnt+1; i++)
        {
            parent[i] = i; // 초기에 모든 노드들을 자기 자신을 부모 노드로 가지고 있음
        }
        kruskal();
    }
    static void marking()
    {
        boolean[][] visit = new boolean[n][m];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] != 0 && !visit[i][j])
                {
                    visit[i][j] = true;
                    map[i][j] = num;
                    landCnt++;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i,j});
                    while (!q.isEmpty())
                    {
                        int[] cur = q.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        for(int dir=0; dir<4; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];
                            if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == 0) continue;
                            q.add(new int[]{nX,nY});
                            visit[nX][nY] = true;
                            map[nX][nY] = num;
                        }
                    }
                    num++;
                }
            }
        }
    }
    static void buildBridge()
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] != 0)
                {
                    for(int dir=0; dir<4; dir++)
                    {
                        findBridge(i,j,dir);
                    }
                }
            }
        }
    }
    static void findBridge(int x,int y,int dir)
    {
        int startNum = map[x][y];
        int dist = 0;
        while (true)
        {
            x += dx[dir];
            y += dy[dir];
            if(OOB(x,y) || map[x][y] == startNum) break;
            if(map[x][y] == 0) dist++;
            else if(map[x][y] != startNum)
            {
                if(dist >= 2)
                {
                    int endNum = map[x][y];
                    pq.add(new int[]{startNum, endNum, dist});
                }
                break;
            }
        }
    }
    static void kruskal()
    {
        int totWeight = 0;
        int isCon = 0;

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int from = cur[0];
            int to = cur[1];
            int weight = cur[2];
            //두 노드가 연결되어 있지 않다면
            if(find(from) != find(to))
            {
                union(from,to);
                totWeight += weight;
                isCon++;
            }
        }
        if(isCon != landCnt-1)
        {
            System.out.print(-1);
        }
        else
            System.out.print(totWeight);
    }
    static int find(int x)
    {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]); // 경로압축
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
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >=m;
    }
}
/*
 static void prim() {
        boolean[] visit = new boolean[landNum]; // 섬 방문 체크 (섬 번호는 1부터 시작)
        PriorityQueue<int[]> edgePQ = new PriorityQueue<>(Comparator.comparingInt(o -> o[2])); // 다리 정보를 담을 우선순위 큐

        // 1번 섬에서 시작하므로 1번 섬과 연결된 모든 다리를 우선순위 큐에 추가
        visit[1] = true; // 1번 섬을 방문한 것으로 표시

        for (int[] bridge : pq) {
            if (bridge[0] == 1) {  // 1번 섬과 연결된 다리만 큐에 추가
                edgePQ.add(bridge);
            }
        }

        int totalCost = 0;    // 총 비용
        int bridgesUsed = 0;  // 연결된 다리 개수

        // 우선순위 큐가 빌 때까지 반복
        while (!edgePQ.isEmpty()) {
            int[] curBridge = edgePQ.poll(); // 가장 작은 비용의 다리를 꺼냄
            int fromIsland = curBridge[0];
            int toIsland = curBridge[1];
            int cost = curBridge[2];

            // 방문하지 않은 섬이 있다면 다리를 연결
            if (!visit[toIsland]) {
                visit[toIsland] = true;  // 해당 섬 방문 처리
                totalCost += cost;       // 비용 추가
                bridgesUsed++;           // 다리 개수 추가

                // 해당 섬에서 이어지는 다른 다리들을 우선순위 큐에 추가
                for (int[] bridge : pq) {
                    if (bridge[0] == toIsland && !visit[bridge[1]]) { // 아직 방문하지 않은 섬과 연결된 다리만 추가
                        edgePQ.add(bridge);
                    }
                }

                // 모든 섬을 연결했으면 종료
                if (bridgesUsed == landNum - 2) { // landNum-1개의 섬 중 하나 제외하고 모두 연결됨
                    System.out.println(totalCost);
                    return;
                }
            }
        }

        // 모든 섬을 연결할 수 없는 경우
        System.out.println(-1);
    }
*/
