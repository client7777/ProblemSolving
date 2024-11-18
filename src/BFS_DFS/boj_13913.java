package BFS_DFS;

import java.io.*;
import java.util.*;
//숨바꼭질4
public class boj_13913 {
    static int[] dx = {-1, 1};
    static int[] map;
    static int[] chase;
    static final int max = 100000;
    static int n, k;

    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            map = new int[max + 1];
            chase = new int[max + 1];

        if (n >= k) {
            System.out.println(n - k);
            while (n > k) {
                System.out.print(n-- + " ");
            }
            System.out.println(k); // 출구도 출력
            return;
        }
        Arrays.fill(map, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        map[n] = 0;
        chase[n] = -1;

        while(!q.isEmpty())
        {
            int cur = q.poll();
            if(cur == k)
            {
                System.out.println(map[k]);
                chasePath(cur);
                return;
            }
            for(int i=0; i<3; i++)
            {
                int next;
                if(i == 2) next = cur * 2;
                else next = cur + dx[i];
                if(next >= 0 && next <= max && map[next] == -1)
                {
                    q.add(next);
                    map[next] = map[cur] + 1;
                    chase[next] = cur;
                }
            }
        }
    }
    static void chasePath(int end)
    {
        Stack<Integer> stack = new Stack<>();
        for(int i = end; i != -1; i = chase[i])
        {
            stack.add(i);
        }
        while(!stack.isEmpty())
        {
            System.out.print(stack.pop() + " ");
        }
    }
}
