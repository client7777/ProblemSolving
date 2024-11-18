package Backtracking;
//소문난 칠공주
//25개의 자리중에 무작위로 7자리를 뽑고 Y가 4개 이상 있는지, 가로, 세로로 연결되어 있는지 BFS로 탐색
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class boj_1941
{
    static int ans = 0;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] arr = new char[5][5];
    static int[] comb = new int[7];
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<5;i++)
        {
            String str = br.readLine();
            for(int j=0;j<5;j++)
            {
                arr[i][j] = str.charAt(j);
            }
        }
        //idx = 현재 고르고 있는 자리의 인덱스를 나타냄, 중복방지 역할
        //depth = 현재 몇명을 골랐는지 나타냄
        dfs(0,0,0);
        System.out.println(ans);
    }
    static void dfs(int idx, int depth, int yCnt)
    {
        if(yCnt >= 4)
        {
            return;
        }
        if(depth == 7)
        {
            if(bfs())
            {
                ans++;
            }
            return;
        }
        for(int i=idx; i<25; i++)
        {
            int x = i/5;
            int y = i%5;
            comb[depth] = i;
            //만약 Y를 뽑았으면 재귀호출시 yCnt++
            dfs(i+1, depth+1, yCnt + (arr[x][y] == 'Y' ? 1 : 0));
        }
    }
    static boolean bfs()
    {
        boolean[] visit = new boolean[7];
        Queue<Integer> q = new LinkedList<>();
        q.add(comb[0]);
        visit[0] = true;
        int count = 1;
        while(!q.isEmpty())
        {
            int cur = q.poll();
            int x = cur/5;
            int y = cur%5;
            for(int dir=0; dir<4; dir++)
            {
                int nX = x + dx[dir];
                int nY = y + dy[dir];
                int next = nX*5 + nY;
                if(OOB(nX,nY)) continue;
                for(int i=0; i<7; i++)
                {
                    if(!visit[i] && comb[i] == next)
                    {
                        visit[i] = true;
                        q.offer(next);
                        count++;
                    }
                }
            }
        }
        return count == 7;
    }
    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= 5 ||  y >= 5;
    }
}
