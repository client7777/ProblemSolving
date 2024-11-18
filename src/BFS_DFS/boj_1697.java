package BFS_DFS;
import java.io.*;
import java.util.*;
//숨바꼭질1
//시간복잡도 = O(N)
//최악의 경우 [0,100000] 모두 탐색해야 할 수 있음
public class boj_1697
{
    static int[] map;
    static boolean[] visit;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[100001];
        visit = new boolean[100001];

        if(n >= k)
        {
            System.out.print(n - k);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visit[n] = true;

        while (!q.isEmpty())
        {
            int cur = q.poll();
            if(cur - 1 >= 0 && !visit[cur - 1] && cur-1 <= map.length-1)
            {
                visit[cur-1] = true;
                q.add(cur-1);
                map[cur-1] = map[cur] + 1;
            }
            if(cur + 1 <= map.length-1 && !visit[cur + 1])
            {
                visit[cur+1] = true;
                q.add(cur+1);
                map[cur+1] = map[cur] + 1;
            }
            if(cur * 2 <= map.length-1 && !visit[cur * 2])
            {
                visit[cur * 2] = true;
                q.add(cur * 2);
                map[cur * 2] = map[cur] + 1;
            }
            if(cur == k)
            {
                System.out.print(map[k]);
            }
        }
    }
}
