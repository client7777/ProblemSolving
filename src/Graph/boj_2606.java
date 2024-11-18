package Graph;
//바이러스
//union-find, bfs, dfs
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2606
{
    static int[] parent;
    static int n,pair;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        pair = Integer.parseInt(br.readLine());

        parent = new int[n+1]; // 1번 컴퓨터부터 n번 컴퓨터까지 정보를 담을 배열

        //각 노드에 대한 부모 노드 초기화 -> 초기에는 자기 자신을 부모 노드로 갖는 원소가 1개인 집합 n개
        for(int i=1; i<=n; i++)
        {
            parent[i] = i;
        }

        for(int i=0; i<pair; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x,y);
        }
        int cnt = 0;
        for(int i=2; i<=n; i++)
        {
            if(find(1) == find(i))
            {
                cnt++;
            }
        }
        System.out.print(cnt);
    }
    static void union(int x,int y)
    {
        x = find(x);
        y = find(y);

        if(x != y)
        {
            if(x > y)
            {
                parent[x] = y;
            }
            else parent[y] = x;
        }
    }
    static int find(int x)
    {
        if(x == parent[x])
        {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}

/*
static int n, pair;
    static int cnt = 0;
    static int[][] arr;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pair = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];
        visit = new boolean[n+1];

        for(int i=0; i<pair; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //인접행렬
            arr[a][b] = 1;
            arr[b][a] = 1;
        }
        System.out.print(bfs(1));
    }
    static int bfs(int i)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        visit[i] = true;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int k=1; k<=n; k++)
            {
                if(arr[cur][k] == 1 && !visit[k])
                {
                    q.add(k);
                    visit[k] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

 dfs 풀이
 static int dfs(int num)
    {
        visit[num] = true;
        cnt++;
        for(int i=1; i<=n; i++)
        {
            if(arr[num][i] == 1 && !visit[i])
            {
                dfs(i);
            }
        }
        return cnt;
    }
 */

/*
인접행렬은 n x n 크기의 2차원 배열로 구성되며, 여기서 n은 그래프의 정점 수입니다.
배열의 각 요소 arr[i][j]는 정점 i에서 정점 j로 가는 간선의 존재 여부를 나타냅니다.
arr[i][j] = 1**이면 정점 i와 정점 j가 연결되어 있음을 의미합니다.
arr[i][j] = 0**이면 연결이 없음을 의미합니다.
*/

/*
 * // DFS를 구현한 예제
 * void dfs(int v) {
 *     visit[v] = true;  // 방문 표시
 *     System.out.print(v + " ");  // 현재 방문한 노드 출력
 *
 *     for (int i = 1; i <= n; i++) {  // 모든 노드 탐색
 *         // 현재 노드 v에서 노드 i로의 간선이 존재하고, i를 방문하지 않았다면
 *         if (arr[v][i] == 1 && !visit[i]) {
 *             dfs(i);  // i 노드로 이동하여 탐색
 *         }
 *     }
 * }
 * // BFS를 구현한 예제
    void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    visit[start] = true;  // 시작 정점 방문 표시

    while (!queue.isEmpty()) {
        int cur = queue.poll();  // 큐에서 현재 노드를 꺼냄
        System.out.print(cur + " ");  // 현재 방문한 노드 출력

        for (int i = 1; i <= n; i++) {  // 모든 노드 탐색
            // 현재 노드 cur에서 노드 i로의 간선이 존재하고, i를 방문하지 않았다면
            if (arr[cur][i] == 1 && !visit[i]) {
                queue.add(i);  // i 노드를 큐에 추가
                visit[i] = true;  // i 노드 방문 표시
            }
        }
    }
}
*/
