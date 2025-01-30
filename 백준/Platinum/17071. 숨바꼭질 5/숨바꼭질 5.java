import java.io.*;
import java.util.*;

public class Main
{
    static int MAX = 500000;  // 최대 범위를 500,000으로 설정
    static int n, k;
    static boolean[][] visit = new boolean[MAX+1][2];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈
        k = Integer.parseInt(st.nextToken()); // 동생

        if (n == k)  // 수빈과 동생이 같은 위치에서 시작할 경우
        {
            System.out.print(0);
            return;
        }
        bfs(n);
    }

    static void bfs(int start)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start][0] = true;

        int time = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            
            k += time;

            if(k > MAX)
            {
                System.out.println(-1);
                return;
            }
            if(visit[k][time%2])
            {
                System.out.print(time);
                return;
            }
            for(int i=0; i<size; i++)
            {
                int cur = q.poll();
                int[] nextPos = {cur-1, cur+1, cur*2};
                for(int next:nextPos)
                {
                    if(next <= MAX && next >= 0 && !visit[next][(time+1)%2])
                    {
                        visit[next][(time+1)%2] = true;
                        q.add(next);
                    }
                }
            }
            time++;
        }
    }
}
