package BFS_DFS;
// 확장 게임
// 루프를 돌면서 더이상 성이 확장이 되지 않는 시점까지 성을 확장
import java.io.*;
import java.util.*;
public class boj_16920
{
    static int[][] map;
    static int[] step = new int[10];
    static int[] area = new int[10];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]>[] player = new Queue[10];
    static int n,m,p;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=p; i++)
        {
            step[i] = Integer.parseInt(st.nextToken());
            player[i] = new LinkedList<>();
        }
        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                char ch = str.charAt(j);
                if(ch == '.')
                {
                    map[i][j] = 1;
                }
                else if(ch == '#')
                {
                    map[i][j] = 0;
                }
                else
                {
                    map[i][j] = 0;
                    player[ch - '0'].add(new int[]{i, j, 0});
                    area[ch - '0'] += 1;
                }
            }
        }
        while (true)
        {
            boolean isExtend = false;
            for(int i=1; i<=p; i++)
            {
                Queue<int[]> next = new LinkedList<>(); // 다음 턴에 확장을 고려한 큐
                while(!player[i].isEmpty())
                {
                    int[] cur = player[i].poll();
                    int curX = cur[0];
                    int curY = cur[1];
                    int curStep = cur[2];
                    if(curStep >= step[i])
                    {
                        next.add(new int[]{curX, curY, 0});
                        continue;
                    }
                    for(int dir=0; dir<4; dir++)
                    {
                        int nX = curX + dx[dir];
                        int nY = curY + dy[dir];
                        if(nX < 0 || nX >= n || nY < 0 || nY >= m) continue;
                        if(map[nX][nY] == 0) continue;
                        player[i].add(new int[]{nX,nY, curStep + 1});
                        map[nX][nY] = 0;
                        area[i] ++;
                        isExtend = true;
                    }
                }
                player[i] = next;
            }
            if(!isExtend) break;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=p; i++)
        {
            sb.append(area[i] + " ");
        }
        System.out.print(sb.toString());
    }
}
