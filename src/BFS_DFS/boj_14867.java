package BFS_DFS;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14867
{
    static int a,b,c,d;
    static HashSet<String> visit = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()); // 물통 A의 용량
        b = Integer.parseInt(st.nextToken()); // 물통 B의 용량
        c = Integer.parseInt(st.nextToken()); // A에 남길 용량
        d = Integer.parseInt(st.nextToken()); // B에 남길 용량

        bfs();
    }

    static void bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0}); // 물통 A에 들어있는 물의 양, 물통 B에 들어있는 물의 양, 작업 수

        while (!q.isEmpty())
        {
            int[] cur = q.poll();

            int curA = cur[0];
            int curB = cur[1];
            int curCnt = cur[2];

            if(curA == c && curB == d)
            {
                System.out.print(curCnt);
                return;
            }
            if(OOB(curA, curB) || is_used(curA, curB)) continue;

            for(int i=0; i<6; i++)
            {
                int nA = curA;
                int nB = curB;

                switch (i)
                {
                    case 0:
                        nA = a;
                        break;
                    case 1:
                        nB = b;
                        break;
                    case 2:
                        nA = 0;
                        break;
                    case 3:
                        nB = 0;
                        break;
                    case 4:
                        if(curA <= b - curB)
                        {
                            nA = 0;
                            nB += curA;
                        }
                        else
                        {
                            nA = curA - (b - curB);
                            nB = b;
                        }
                        break;
                    case 5:
                        if(curB <= a - curA)
                        {
                            nA += curB;
                            nB = 0;
                        }
                        else
                        {
                            nA = a;
                            nB = curB - (a - curA);
                        }
                        break;
                }

                q.add(new int[]{nA,nB,curCnt + 1});
            }
        }
        System.out.print(-1);
    }
    static boolean is_used(int x,int y)
    {
        String point = x + "," + y;
        if(visit.contains(point))
            return true;
        visit.add(point);
        return false;
    }
    static boolean OOB(int x, int y)
    {
        return x < 0 || x > a || y < 0 || y > b;
    }
}
