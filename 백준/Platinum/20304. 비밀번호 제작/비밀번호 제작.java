import java.io.*;
import java.util.*;

public class Main
{
    static int n,m;
    static int[] result;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        result = new int[n+1]; //각 비밀번호의 안전거리를 저장할 배열
        Arrays.fill(result,Integer.MAX_VALUE); // 모든 비밀번호의 초기 안전 거리를 최대값으로 설정

        q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++)
        {
            int p = Integer.parseInt(st.nextToken());
            result[p] = 0; //이미 해커가 시도한 비밀번호는 안전 거리가 0
            q.add(p); // 해커가 시도한 비밀번호에서부터 다른 비밀번호로 이동하면서 안전 거리 계산
        }
        bfs();
        
    }
    static void bfs()
    {
        int max = 0;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int i=0; i<20; i++)
            {
                int nX = cur ^ (1<<i); //현재 비밀번호의 i번째 비트를 뒤집음
                if(nX > n || result[nX] != Integer.MAX_VALUE) continue;
                q.add(nX);
                result[nX] = result[cur] + 1;
                max = Math.max(max, result[nX]);
            }
        }
        System.out.println(max);
    }
}
