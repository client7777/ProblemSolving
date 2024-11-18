 package BFS_DFS;
//적록색약
import java.io.*;
import java.util.*;
public class boj_10026
{
    static int n ;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] map;
    static boolean[][] visit;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt( br.readLine());
        map = new char[n][n];
        visit =  new boolean[n][n];

        for(int i=0; i<n; i++)
        {
            String str = br.readLine();
            for(int j=0; j<n; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        //적록색약이 아닌 사람에 대한 구역을 구하고
        //방문처리 배열 초기화, R -> G or G -> R로 맵 변경한 뒤에 구역을 구해줌
        //두 경우 모두 BFS 수행을 동일하게 수행하므로 BFS 함수를 작성한 뒤에 호출하는 방식으로
        int ans1 = area();
        //G -> R로 바꿔줌
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 'G')
                {
                    map[i][j] = 'R';
                }
            }
        }
        // visit 배열 false 값으로 초기화
        for(int i=0; i<n; i++)
        {
            Arrays.fill(visit[i], false);
        }
        int ans2 = area();
        StringBuilder sb = new StringBuilder();
        sb.append(ans1).append(' ').append(ans2);
        System.out.println(sb.toString());
    }

    static int area()
    {
        int cnt=0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(!visit[i][j])
                {
                    cnt++;
                    bfs(i, j);
                }
            }
        }
        return cnt;
    }
    static void bfs(int startX, int startY)
    {
        visit[startX][startY] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY]) continue;
                if(map[nX][nY] != map[curX][curY]) continue;
                visit[nX][nY] = true;
                q.add(new int[]{nX, nY});
            }
        }
    }
    static boolean OOB(int x, int y)
    {
        return x< 0 || y < 0 || x >= n || y >= n;
    }
}
