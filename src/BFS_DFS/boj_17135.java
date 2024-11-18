package BFS_DFS;
//캐슬 디펜스
import java.io.*;
import java.util.*;

public class boj_17135
{
    static int n,m,d;
    static int ans = 0;
    static ArrayList<int[]> archer = new ArrayList<>();
    static int[][] map;
    static int[] dx = {-1,0,0};
    static int[] dy = {0,1,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n+1][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0,0);
        System.out.print(ans);
    }
    static void comb(int depth, int at)
    {
        if(depth == 3)
        {
            //게임 시작
            simulation();
            return;
        }
        for(int i=at; i<m; i++)
        {
            archer.add(new int[]{n,i});
            comb(depth + 1, i+1);
            archer.remove(archer.size() - 1);
        }
    }
    static void simulation()
    {
        int[][] copyMap = new int[n+1][m];
        for(int i=0; i<n; i++)
        {
            copyMap[i] = map[i].clone();
        }
        int kill = 0;
        while (hasEnemies(copyMap))
        {
            boolean[][] visit = new boolean[n][m];

            for(int[] arc:archer)
            {
                //타겟 탐색
                int[] target = findTarget(arc[0], arc[1], copyMap);
                if(target != null && !visit[target[0]][target[1]])
                {
                    visit[target[0]][target[1]] = true;
                }
            }
            // 타겟 제거
            for(int i=0; i<n; i++)
            {
                for(int j=0; j<m; j++)
                {
                    if(copyMap[i][j] == 1 && visit[i][j])
                    {
                        copyMap[i][j] = 0;
                        kill++;
                    }
                }
            }
            //적들을 아래로 한칸 내림
            gravity(copyMap);
        }
        ans = Math.max(ans, kill);
    }
    static int[] findTarget(int x,int y,int[][] copyMap)
    {
        int[] target = null;
        int minDist = d + 1;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[n+1][m];
        q.add(new int[]{x,y,0});
        visit[x][y] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            if (curDist > 0 && copyMap[curX][curY] == 1)
            {
                if(curDist < minDist || (curDist == minDist && curY < target[1]))
                {
                    minDist = curDist;
                    target = new int[]{curX, curY};
                }
            }
            if(curDist < d)
            {
                for(int dir=0; dir<3; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    if(OOB(nX,nY) || visit[nX][nY]) continue;
                    q.add(new int[]{nX,nY,curDist + 1});
                    visit[nX][nY] = true;
                }
            }
        }
        return target;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y>= m;
    }
    static boolean hasEnemies(int[][] copyMap)
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(copyMap[i][j] == 1) return true;
            }
        }
        return false;
    }
    static void gravity(int[][] copyMap)
    {
        for(int i=n-1; i>0; i--)
        {
            copyMap[i] = copyMap[i-1].clone();
        }
        Arrays.fill(copyMap[0],0);
    }
}
