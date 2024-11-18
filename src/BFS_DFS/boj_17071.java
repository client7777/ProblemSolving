package BFS_DFS;
//숨바꼭질5
import java.io.*;
import java.util.*;

public class boj_17071
{
    static int MAX = 500000;  // 최대 범위를 500,000으로 설정
    static int n, k;
    static boolean[][] visit = new boolean[MAX+1][2]; // 0 = 짝수 1 = 홀수
    //1차원 배열로 설정하면 수빈이와 동생이 같은 위치에 같은 시간에 도달해도 만날 수 없는 상황이 발생
    //1차원 배열로 방문 여부를 체크하면 그 후 홀수 시간에도 수빈이가 그 위치를 다시 방문하는 것을 기록할 수 없기 때문에,
    //동생이 그 위치에 홀수 시간에 도착해도 수빈이와 만났다는 기록을 남길 수 없습니다.
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

    //수빈이가 x초에 p지점에 도달했으면 x+2초에도 x지점에 도달할 수 있다.
    //수빈이가 먼저 x초에 p지점에 도달한 이력이 있고 동생도 x초에 p지점에 도달한다면 둘은 만날 수 있다.
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
            if(visit[k][time%2]) // 수빈이가 동생이 있는 위치에 동일한 시간대에 이미 방문했는지 체크
            {
                System.out.println(time);
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
