package Graph;
// 연결 요소의 개수
import java.io.*;
import java.util.*;

public class boj_11724
{
    static int n,m; // 노드, 간선의 개수
    static List<Integer>[] adList;
    static boolean[] visit;
    static int cnt = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adList = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        visit = new boolean[n+1];

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
            //1~n번까지 모든 노드를 돌면서 방문처리되지 않은 노드를 발견할 경우 개수를 증가
            //방문하지 않은 노드를 발견했다는 것은 새로운 연결요소를 발견했다는 의미
            if(!visit[i])
            {
                bfs(i);
                cnt++;
            }
        }
        
        System.out.print(cnt);
    }

    static void bfs(int num)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        visit[num] = true;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next:adList[cur])
            {
                if(!visit[next])
                {
                    q.add(next);
                    visit[next] = true;
                }
            }
        }
    }
}
