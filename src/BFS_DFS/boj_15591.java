package BFS_DFS;
//시간복잡도 = O(q * (n + e)) 약 50000000
import java.io.*;
import java.util.*;

public class boj_15591
{
    static int n,q;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<int[]>[] adList;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        // 간선의 개수
        for(int i=0; i<n-1; i++)
        {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken()); // 가중치
            
            //양방향 그래프
            adList[p].add(new int[]{q,r});
            adList[q].add(new int[]{p,r});
        }
        //질문의 개수
        for(int i=0; i<q; i++)
        {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bfs(k,v);
        }
        System.out.print(sb);
    }
    static void bfs(int k,int v)
    {
        visit = new boolean[n+1];
        visit[v] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(v);

        int cnt = 0;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int[] a:adList[cur])
            {
                int nextNode = a[0];
                int weight = a[1];
                //방문하지 않고 가중치가 usado 이상인 노드만 탐색
                if(!visit[nextNode] && weight >= k)
                {
                    cnt++;
                    visit[nextNode] = true;
                    q.add(nextNode);
                }
            }
        }
        sb.append(cnt).append('\n');
    }
}
