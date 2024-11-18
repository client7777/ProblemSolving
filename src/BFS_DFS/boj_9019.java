package BFS_DFS;
//DSLR
import java.io.*;
import java.util.*;

public class boj_9019
{
    static int test_case;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 노드
            int b = Integer.parseInt(st.nextToken()); // 목적지 노드

            bfs(a,b);
        }
        System.out.print(sb);
    }
    static void bfs(int start, int end)
    {
        final int MAX = 10000;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean[] visit = new boolean[MAX];
        visit[start] = true;
        String[] ans = new String[MAX];
        Arrays.fill(ans, "");

        while (!q.isEmpty())
        {
            int cur = q.poll();

            int D = (cur*2) % MAX;
            int S = (cur == 0)? 9999: cur-1;
            int L = (cur%1000) * 10 + cur /1000;
            int R = (cur%10) * 1000 + cur /10;

            if(!visit[D])
            {
                q.add(D);
                visit[D] = true;
                ans[D] = ans[cur] + "D";
            }
            if(!visit[S])
            {
                q.add(S);
                visit[S] = true;
                ans[S] = ans[cur] + "S";
            }
            if(!visit[L])
            {
                q.add(L);
                visit[L] = true;
                ans[L] = ans[cur] + "L";
            }
            if(!visit[R])
            {
                q.add(R);
                visit[R] = true;
                ans[R] = ans[cur] + "R";
            }
        }
        sb.append(ans[end]).append('\n');
    }
}
