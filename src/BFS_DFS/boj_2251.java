package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_2251
{
    static int a,b,c;
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        bfs();

        Collections.sort(ans);
        for(int val:ans)
        {
            System.out.print(val + " ");
        }
    }
    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visit = new boolean[a+1][b+1][c+1];
        q.add(new int[]{0,0,c}); // a,b 물통은 비어있고 c 물통은 가득찬 상태
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curA = cur[0];
            int curB = cur[1];
            int curC = cur[2];
            // 큐에 추가할 때 매번 방문체크를 하지 않고 일단 큐에 좌표를 추가하고
            // 큐에서 좌표를 꺼냈을 때 이미 방문한 좌표라면 넘김
            if(visit[curA][curB][curC]) continue;
            visit[curA][curB][curC] = true; // 방문하지 않은 좌표면 방문처리
            if(curA == 0)
            {
                ans.add(curC);
            }
            //b->a
            if(curA + curB <= a)
            {
                q.add(new int[]{curA + curB, 0, curC});
            }
            else q.add(new int[]{a, curA + curB - a, curC});

            //c->a
            if(curA + curC <= a)
            {
                q.add(new int[]{curA + curC, curB, 0});
            }
            else q.add(new int[]{a, curB, curA+curC - a});

            //a->b
            if(curA + curB <= b)
            {
                q.add(new int[]{0, curA + curB, curC});
            }
            else q.add(new int[]{curA + curB - b, b, curC});

            //c->b
            if(curB + curC <= b)
            {
                q.add(new int[]{curA, curB + curC, 0});
            }
            else q.add(new int[]{curA, b, curB + curC - b});

            //a->c
            if(curA + curC <= c)
            {
                q.add(new int[]{0, curB, curA + curC});
            }
            else q.add(new int[]{curA + curC - c, curB, c});

            //b->c
            if(curB + curC <= c)
            {
                q.add(new int[]{curA , 0, curB + curC});
            }
            else q.add(new int[]{curA, curB + curC - c, c});
        }
    }
}
