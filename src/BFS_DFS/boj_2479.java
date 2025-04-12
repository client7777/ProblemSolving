package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2479
{
    static int n,k;
    static String[] arr;
    static int[] prev;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        prev = new int[n+1];
        Arrays.fill(prev, -1);

        arr = new String[n+1];
        for(int i=1; i<=n; i++)
        {
            arr[i] = br.readLine();
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        if(bfs(start, end))
        {
            Stack<Integer> stack = new Stack<>();
            int cur = end;

            while (cur != -1)
            {
                stack.add(cur);
                cur = prev[cur];
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty())
            {
                sb.append(stack.pop()).append(" ");
            }

            System.out.print(sb);
        }
        else System.out.print(-1);
    }

    static boolean bfs(int start, int end)
    {
        boolean[] visit = new boolean[n+1];
        visit[start] = true;
        prev[start] = -1;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, arr[start]));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curIdx = cur.idx;
            String curNode = cur.node;

            if(curIdx == end) return true;

            for(int i=1; i<=n; i++)
            {
                if(visit[i]) continue;

                int nextIdx = i;
                String nextNode = arr[i];

                if(hammingDistance(curNode, nextNode) == 1)
                {
                    q.add(new Node(nextIdx, nextNode));
                    visit[nextIdx] = true;
                    prev[nextIdx] = curIdx;
                }
            }
        }
        return false;
    }

    static int hammingDistance(String str1, String str2)
    {
        int cnt = 0;
        for(int i=0; i<k; i++)
        {
            if(str1.charAt(i) != str2.charAt(i)) cnt++;
        }
        return cnt;
    }
    static class Node
    {
        int idx;
        String node;

        public Node(int idx, String node)
        {
            this.idx = idx;
            this.node = node;
        }
    }
}
