package BFS_DFS;
import java.io.*;
import java.util.*;
//어차피 맵에서 1로 표시된 곳만 탐색하면서 단지의 번호를 붙여주고 area를 계산하기 때문에
//따로 boolean형 visit 배열을 사용할 필요가 없다.
//마킹과 면적을 구하는 행동을 굳이 따로 하지 않아도 된다.
public class boj_2667
{
    static int n;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i = 0; i < n; i++)
        {
            String str = br.readLine();
            for(int j = 0; j < n; j++)
            {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        int cnt = 0;
        int num = 2;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(map[i][j] == 1)
                {
                    cnt++;
                    int area = mark(i,j,num++);
                    list.add(area);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        Collections.sort(list);
        for(int val:list)
        {
            sb.append(val).append('\n');
        }
        System.out.print(sb);
    }
    static int mark(int x, int y, int num)
    {
        int area = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        map[x][y] = num;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || map[nX][nY] != 1) continue;
                q.add(new int[]{nX, nY});
                map[nX][nY] = num;
                area++;
            }
        }
        return area;
    }

    static boolean OOB(int x, int y)
    {
    return x < 0 || y < 0 || x >= n || y >= n;
    }
}
