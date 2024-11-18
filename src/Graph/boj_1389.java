package Graph;
//케빈 베이컨의 6단계 법칙
import java.io.*;
import java.util.*;
//시작 노드를 기준으로 현재 노드에서 새로운 노드에 방문할 때마다 거리를 합산해서 최소값을 가지고 있는 인덱스를 구함
public class boj_1389
{
    static int n,m; // 노드의 수, 간선의 수
    static ArrayList<Integer>[] adList;
    static int[] ans;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = new int[n+1];
        adList = new ArrayList[n + 1];
        for(int i = 1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=1; i<=m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adList[x].add(y);
            adList[y].add(x);
        }
        for(int i=1; i<=n; i++)
        {
            bfs(i);
        }
        int minVale = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i=1; i<=n; i++)
        {
            if(ans[i] < minVale)
            {
                minVale = ans[i];
                minIndex = i;
            }
        }
        System.out.print(minIndex);
    }
    static void bfs(int start)
    {
        boolean[] visit = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0}); // 시작 노드와 거리를 저장
        visit[start] = true;

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curDist = cur[1];
            
            //현재 노드에서 다른 모든 노드에 대한 거리 누적
            ans[start] += curDist;

            for(int next:adList[curX])
            {
                if(!visit[next])
                {
                    q.add(new int[]{next, curDist + 1});
                    visit[next] = true;
                }
            }
        }
    }

}
