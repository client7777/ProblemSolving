package BFS_DFS;
//스타트링크, 숨바꼭질과 똑같은 문제.
//1차원 배열에서 목적지 찾기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class boj_5014
{
    static int f,s,g,u,d;
    static int[] map;
    static boolean[] visit;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken()); // 건물의 높이
        s = Integer.parseInt(st.nextToken()); // 강호가 있는 층
        g = Integer.parseInt(st.nextToken()); // 목적지
        u = Integer.parseInt(st.nextToken()); // 위로 u칸 이동 가능
        d = Integer.parseInt(st.nextToken()); // 아래로 d칸 이동 가능
        map = new int[f+1]; // 층은 1층부터 f에 1층을 더해줌
        visit = new boolean[f+1];

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visit[s] = true;

        while(!q.isEmpty())
        {
            int cur = q.poll();
            if(cur==g)
            {
                System.out.print(map[cur]);
                return;
            }
            if(cur + u <= map.length-1 && !visit[cur+u])
            {
                q.add(cur+u);
                visit[cur + u] = true;
                map[cur + u] = map[cur] + 1;
            }
            if(cur - d >= 1 && !visit[cur-d])
            {
                q.add(cur-d);
                visit[cur-d] = true;
                map[cur - d] = map[cur] + 1;
            }
        }
        System.out.print("use the stairs");
    }
}
