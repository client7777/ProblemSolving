package Graph;
//텀 프로젝트
import java.io.*;
import java.util.*;

public class boj_9466
{
    static int n;
    static int cnt;
    static int[] arr;
    static boolean[] visit;
    static boolean[] finished;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int i=0; i<tc; i++)
        {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            visit = new boolean[n+1];
            finished = new boolean[n+1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++)
            {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for(int j=1; j<=n; j++)
            {
                if(!visit[j])
                {
                    dfs(j);
                }
            }
            System.out.println(n - cnt);
        }
    }

    static void dfs(int now)
    {
        visit[now] = true;
        int next = arr[now];

        // 다음 노드가 방문하지 않은 노드라면
        if(!visit[next])
        {
            dfs(next);

        }
        else
        {
            if(!finished[next])
            {
                cnt++;
                for(int i = next; i != now; i = arr[i])
                {
                    cnt++;
                }
            }
        }
        finished[now] = true;
    }
}
