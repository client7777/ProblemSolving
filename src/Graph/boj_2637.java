package Graph;

import java.io.*;
import java.util.*;

public class boj_2637
{
    static int n,m;
    static ArrayList<int[]>[] graph;
    static int[] inDegree, cnt;
    static boolean[] isBase;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        inDegree = new int[n+1];
        cnt = new int[n+1];
        isBase = new boolean[n+1];
        Arrays.fill(isBase, true);
        StringTokenizer st;
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            isBase[x] = false; // x번 부품은 기본 부품이 아님
            graph[x].add(new int[]{y,k}); // x번 부품을 만들기 위해서는 y번 부품 k개가 필요
            inDegree[y]++;
        }

        solve();
    }
    static void solve()
    {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        cnt[n] = 1;

        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int[] next:graph[cur])
            {
                int nextNode = next[0];
                int parts = next[1];

                cnt[nextNode] += parts * cnt[cur];
                inDegree[nextNode]--;
                if(inDegree[nextNode] == 0)
                {
                    q.add(nextNode);
                }
            }
        }
        for(int i=1; i<=n; i++)
        {
            if(isBase[i])
            {
                sb.append(i).append(" ").append(cnt[i]).append('\n');
            }
        }
        System.out.print(sb);
    }
}
