import java.io.*;
import java.util.*;

public class Main
{
    static int n,m,r; //노드, 간선, 시작노드
    static ArrayList<Integer>[] adList;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드
        m = Integer.parseInt(st.nextToken()); // 간선
        r = Integer.parseInt(st.nextToken()); // 시작노드
        visit = new boolean[n+1];
        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        //양방향 간선
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y  =Integer.parseInt(st.nextToken());
            adList[x].add(y);
            adList[y].add(x);
        }
        for(int i=1; i<=n; i++)
        {
            Collections.sort(adList[i],Collections.reverseOrder());
        }
        bfs(r);
        System.out.print(sb);
    }
    static void bfs(int start)
    {
        int cnt = 1;
        int[] order = new int[n+1];
        Arrays.fill(order, 0);
        order[start] = cnt++;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
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
            sb.append(order[i]).append('\n');
        }
    }
}
