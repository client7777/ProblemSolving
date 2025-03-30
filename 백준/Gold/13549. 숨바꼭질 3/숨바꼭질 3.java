
import java.io.*;
import java.util.*;
//숨바꼭질
public class Main
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
