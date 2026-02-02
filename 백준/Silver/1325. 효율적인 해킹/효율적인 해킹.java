import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static List<Integer>[] adList;
    static int[] ans;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adList = new List[n+1];
        ans = new int[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adList[x].add(y);
        }
        for(int i=1; i<=n; i++)
        {
            visit = new boolean[n+1];
            bfs(i);
        }
        int max = 0;
        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, ans[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            if(max == ans[i])
            {
                sb.append(i + " ");
            }
        }
        System.out.print(sb);
    }
    static void bfs(int n)
    {
        visit[n] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:adList[cur])
            {
                if(!visit[next])
                {
                    q.add(next);
                    visit[next] = true;
                    ans[next]++;
                }
            }
        }
    }
}
