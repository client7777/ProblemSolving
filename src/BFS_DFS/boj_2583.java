package BFS_DFS;
import java.io.*;
import java.util.*;
// 영역 구하기
// 그림 문제와의 차이점은 이 문제는 맵에 마킹이 되어있지 않고 나머지 구간에 대한 문제
// 직사각형을 주어진 좌표를 통해서 마킹 -> bfs를 통해서 직사각형을 제외한 나머지 구간의 개수를 구하고 면적을 구함
// 여러 개의 답을 한 공간에 담아두고 정렬해서 출력할 경우 ArrayList 유용
// Collections.sort(list); -> ArrayList 오름차순 Collections.sort(list, Collections.reverseOrder()); -> 내림차순
public class boj_2583
{
    static int m,n,k;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] arsgs) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        visited = new boolean[m][n];

        for(int i=0; i<k; i++)
        {
            st = new StringTokenizer(br.readLine());
            int left_x = Integer.parseInt(st.nextToken());
            int left_y = Integer.parseInt(st.nextToken());
            int right_x = Integer.parseInt(st.nextToken());
            int right_y = Integer.parseInt(st.nextToken());
            marking(left_x, left_y, right_x, right_y);
        }
        int cnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(map[i][j] == 0 && !visited[i][j])
                {
                    cnt++;
                    int area = bfs(i, j);
                    list.add(area);
                }
            }
        }
        Collections.sort(list);
        sb.append(cnt).append("\n");
        for(int val:list)
        {
            sb.append(val).append(" ");
        }
        System.out.print(sb);
    }
    static int bfs(int x,int y)
    {
        int area = 1;
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[]{x,y});
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || map[nX][nY] != 0 || visited[nX][nY]) continue;
                q.offer(new int[]{nX,nY});
                visited[nX][nY] = true;
                area++;
            }
        }
        return area;
    }
    static void marking(int left_x, int left_y, int right_x, int right_y)
    {
        for(int i = left_y; i<right_y; i++)
        {
            for(int j = left_x; j<right_x; j++)
            {
                map[i][j] = 1;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}