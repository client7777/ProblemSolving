package BFS_DFS;
//안전도가 제일 높은 비밀번호의 안전도
import java.io.*;
import java.util.*;

public class boj_20304
{
    static int n,m;
    static int[] safe;
    static ArrayList<Integer> start = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        safe = new int[n+1]; // 1~n까지의 안전도를 저장할 배열
        Arrays.fill(safe,-1); // 방문 여부 체크를 위해 -1로 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            int p = Integer.parseInt(st.nextToken()); // 로그인 시도에 사용된 비밀번호값
            safe[p] = 0;
            start.add(p);
        }
        bfs();
    }
    static void bfs()
    {
        int max = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int startNode:start)
        {
            q.add(startNode);
        }
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int i=0; i<20; i++)
            {
                int nX = cur ^ (1<<i); //XOR 연산. 자릿수가 같으면 0, 다르면 1
                if(nX > n || safe[nX] != -1) continue;
                q.add(nX);
                safe[nX] = safe[cur]+1;
                max = Math.max(max, safe[nX]);
            }
        }
        System.out.print(max);
    }
}
