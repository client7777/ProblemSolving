import java.io.*;
import java.util.*;

public class Main
{
    static int n,m,r;
    static ArrayList<Integer>[] adList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adList[a].add(b);
            adList[b].add(a);
        }
        for(int i=1; i<=n; i++)
        {
            Collections.sort(adList[i]);
        }
        bfs(r);
        System.out.print(sb);
    }
    static void bfs(int r)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(r);
        boolean[] visit = new boolean[n+1];
        visit[r] = true;
        int[] order = new int[n+1];
        Arrays.fill(order, 0); // 방문하지 않은 노드 0으로 초기화
        int cnt = 1;
        order[r] = cnt++;

        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:adList[cur])
            {
                if(!visit[next])
                {
                    q.add(next);
                    visit[next] = true;
                    order[next] = cnt++;
                }
            }
        }
        for(int i=1; i<=n; i++)
        {
            sb.append(order[i] + "\n");
        }
    }
}
