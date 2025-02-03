import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n,m,mid; // n은 홀수
    static ArrayList<Integer>[] heavy,light;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mid = (n+1)/2;

        heavy = new ArrayList[n+1];
        light = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
        {
            heavy[i] = new ArrayList<>();
            light[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            heavy[u].add(v); // u는 v보다 무겁다.
            light[v].add(u); // v는 u보다 가볍다.
        }
        int ans = 0;
        for(int i=1; i<=n; i++)
        {
            if(isHeavyOrLight(i,heavy) || isHeavyOrLight(i,light))
            {
                ans++;
            }
        }
        System.out.print(ans);
    }
    // 자신보다 무겁거나 가벼운 구슬이 (n+1)/2개가 넘어가면 그 구슬은 중간인 무게가 될 수 없다.
    static boolean isHeavyOrLight(int start, ArrayList<Integer>[] graph)
    {
        int cnt = 0;
        visit = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:graph[cur])
            {
                if(!visit[next])
                {
                    q.add(next);
                    visit[next] = true;
                    cnt++;
                }
            }
        }
        return cnt >= mid;
    }
}
