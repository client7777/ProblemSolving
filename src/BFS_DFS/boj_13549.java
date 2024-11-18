package BFS_DFS;
//숨바꼭질3
import java.io.*;
import java.util.*;
//점프를 하는 경우 가중치가 0이므로 최단거리 탐색을 위해서 점프를 하는 경우의 좌표가
//큐에 우선적으로 삽입되어야 한다.
public class boj_13549
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
            System.out.print(n-k);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(n); // 수빈의 좌표를 큐에 추가
        visit[n] = true; // 방문처리
        while(!q.isEmpty())
        {
            int cur = q.poll();

            if(cur*2 <= map.length - 1 && !visit[cur*2])
            {
                q.add(cur * 2);
                visit[cur * 2] = true;
                map[cur * 2] = map[cur];

            }

            if(cur - 1 >= 0 && cur -1 <= map.length - 1 && !visit[cur - 1])
            {
                q.add(cur-1);
                visit[cur-1] = true;
                map[cur-1] = map[cur] + 1;
            }
            if(cur+1 <= map.length - 1 && !visit[cur+1])
            {
                q.add(cur + 1);
                visit[cur+1] = true;
                map[cur+1] = map[cur] + 1;
            }
            if(cur == k)
            {
                System.out.print(map[cur]);
                return;
            }
        }
    }
}
