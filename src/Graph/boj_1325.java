package Graph;
//효율적인 해킹
import java.io.*;
import java.util.*;

public class boj_1325
{
    static int n,m;
    static int[] ans; // 신뢰도를 저장할 배열
    static ArrayList<Integer>[] adList;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException
    {
        //A가 B를 신뢰한다. B를 해킹하면 A도 해킹이 가능하다. 한 번에 가장 많은 컴퓨터를 해킹하기 위해서는
        //신뢰를 가장 많이 받는 컴퓨터를 해킹하면 된다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //컴퓨터의 번호
        m = Integer.parseInt(st.nextToken()); //노드의 개수

        ans = new int[n+1];
        adList = new ArrayList[n+1];

        //1번 ~ n번인덱스까지 사용
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        //단방향그래프, a에서 b방향으로 화살표, 가장 많은 화살표를 받은 노드의 번호를 알아냄
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adList[a].add(b);
        }
        for(int i=1; i<=n; i++)
        {
            bfs(i);
        }
        for(int i=1; i<=n; i++)
        {
            max = Math.max(max, ans[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            if(ans[i] == max)
            {
                sb.append(i + " ");
            }
        }
        System.out.print(sb);
    }
    static void bfs(int start)
    {
        boolean[] visit = new boolean[n+1];
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
                    ans[next] ++;
                }
            }
        }
    }
}
