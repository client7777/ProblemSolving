import java.io.*;
import java.util.*;

public class Main
{
    static int test_case, n;
    static ArrayList<int[]> convi;
    static int festivalX, festivalY;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());
        for(int t=0; t<test_case; t++)
        {
            // 편의점의 개수
            n = Integer.parseInt(br.readLine());

            // 집의 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            int homeX = Integer.parseInt(st.nextToken());
            int homeY = Integer.parseInt(st.nextToken());

            convi = new ArrayList<>();
            for(int i=0; i<n; i++)
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                convi.add(new int[]{x,y});
            }
            st = new StringTokenizer(br.readLine());
            festivalX = Integer.parseInt(st.nextToken());
            festivalY = Integer.parseInt(st.nextToken());
            bfs(homeX, homeY);
        }
        System.out.print(sb);
    }
    static void bfs(int x, int y)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        boolean[] visit = new boolean[n]; // 편의점 방문 여부
        while (!q.isEmpty())
        {
            //맥주 20병으로 이동가능한 거리는 맨해튼 거리 1000미터이다.
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            //현재 좌표가 행사장과 맨해튼 거리 1000이내에 있으면
            if(Math.abs(curX - festivalX) + Math.abs(curY - festivalY) <= 1000)
            {
                sb.append("happy\n");
                return;
            }
            //현재 좌표가 행사장과 맨해튼 거리 1000이내에 있지 않다면
            //편의점에 방문을 시도
            for(int i=0; i<n; i++)
            {
                int[] store = convi.get(i);
                int storeX = store[0];
                int storeY = store[1];
                //방문하지 않은 편의점이거나, 맨해튼거리 1000미터 이내에 있다면
                if(!visit[i] && Math.abs(curX - storeX) + Math.abs(curY - storeY) <= 1000)
                {
                    q.add(new int[]{storeX, storeY});
                    visit[i] = true;
                }
            }
        }
        sb.append("sad\n");
    }
}
