package BFS_DFS;
//돌 그룹
import java.io.*;
import java.util.*;

public class boj_12886
{
    static int a,b,c;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.print(bfs(a,b,c)?1:0);
    }
    static boolean bfs(int a, int b, int c)
    {
        //s = a + b + c 라고 했을 때, 세 개의 돌의 개수를 같게 만들려면 각 돌의 개수는 s/3개가 되어야 함.
        //s가 3의 배수가 아니라면 s/3는 정수가 아니게 되고 a,b,c는 값이 정수인 돌의 개수이기 때문에 s/3로 각 개수를 맞추지 못함
        int sum = a + b + c;
        if((sum%3) != 0) return false;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a,b,c});
        boolean[][] visit = new boolean[1501][1501]; // 3개의 돌중에 2개의 돌만 뽑아서 탐색하므로 2차원 배열로 설정
        visit[a][b] = true;
        
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curA = cur[0];
            int curB = cur[1];
            int curC = cur[2];

            if(curA == curB && curB == curC) return true;

            if(curA != curB)
            {
                int nA = curA > curB ? curA - curB : curA * 2;
                int nB = curB > curA ? curB - curA : curB * 2;
                if(nA <= 1500 && nB <= 1500 && !visit[nA][nB])
                {
                    visit[nA][nB] = true;
                    q.add(new int[]{nA,nB, curC});
                }
            }
            if(curA != curC)
            {
                int nA = curA > curC ? curA - curC : curA * 2;
                int nC = curC > curA ? curC - curA : curC * 2;
                if(nA <= 1500 && nC <= 1500 && !visit[nA][nC])
                {
                    visit[nA][nC] = true;
                    q.add(new int[]{nA,curB, nC});
                }
            }
            if(curB != curC)
            {
                int nB = curB > curC ? curB - curC : curB * 2;
                int nC = curC > curB ? curC - curB : curC * 2;
                if(nB <= 1500 && nC <= 1500 && !visit[nB][nC])
                {
                    visit[nB][nC] = true;
                    q.add(new int[]{curA, nB,nC});
                }
            }
        }
       return false;
    }
}
