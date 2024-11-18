package BFS_DFS;
// A -> B
import java.io.*;
import java.util.*;

public class boj_16953
{
    //overflow 방지를 위해서 long타입으로 선언
    static long a,b;
    static int cnt = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        System.out.print(bfs(a,b));
    }
    static int bfs(long start, long target)
    {
        Queue<long[]> q = new LinkedList<>();
        q.add(new long[]{start, 1});
        while (!q.isEmpty())
        {
            long[] cur = q.poll();
            long num = cur[0];
            int cnt = (int)cur[1];

            if(num == target)
            {
                return cnt;
            }

            long n1 = num * 2; // 2배 연산
            long n2 = num*10 +1; //숫자 끝에 1을 붙이는 연산

            if(n1 <= target)
            {
                q.add(new long[]{n1, cnt + 1});
            }
            if(n2 <= target)
            {
                q.add(new long[]{n2, cnt + 1});
            }
        }
        return -1;
    }
}
