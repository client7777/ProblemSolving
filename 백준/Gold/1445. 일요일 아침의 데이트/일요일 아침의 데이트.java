import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
    static int n, m;
    static int startX, startY, endX, endY;
    static char[][] map;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < m; j++)
            {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'S')
                {
                    startX = i;
                    startY = j;
                }

                if (map[i][j] == 'F')
                {
                    endX = i;
                    endY = j;
                }
            }
        }

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 'g')
                {
                    for(int dir=0; dir<4; dir++)
                    {
                        int x = i + dx[dir];
                        int y = j + dy[dir];
                        if(OOB(x,y)) continue;
                        if(map[x][y] == 'g') continue;
                        map[x][y] = 'h';
                    }
                }
            }
        }

        dijkstra();
        System.out.print(sb);
    }

    static void dijkstra()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) return o1[3] - o2[3];  // 쓰레기 칸 수, 인접 쓰레기 칸 수로 우선순위 설정
            return o1[2] - o2[2];
        });
        pq.add(new int[]{startX, startY, 0, 0}); // x, y, 쓰레기 칸 수, 인접 쓰레기 칸 수

        boolean[][] visit = new boolean[n][m];
        visit[startX][startY] = true;

        while (!pq.isEmpty())
        {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];
            int curSide = cur[3];

            // 목표 위치에 도달한 경우 출력
            if (curX == endX && curY == endY)
            {
                sb.append(curCnt).append(" ").append(curSide);
                return;
            }

            for (int dir = 0; dir < 4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if (OOB(nX, nY) || visit[nX][nY]) continue;

                // 쓰레기 칸에 도달한 경우
                if (map[nX][nY] == 'g')
                {
                    pq.add(new int[]{nX, nY, curCnt + 1 , curSide});
                    visit[nX][nY] = true;
                }
                // 쓰레기와 인접한 빈칸인 경우 (S와 F는 제외)
                else if (map[nX][nY] == 'h')
                {
                    // 시작점(S)이나 목표점(F)일 경우 인접 쓰레기 칸 수를 증가시키지 않음
                    if (nX == startX && nY == startY)
                        pq.add(new int[]{nX, nY, curCnt, curSide});  // 'S'는 카운트하지 않음
                    else if (nX == endX && nY == endY)
                        pq.add(new int[]{nX, nY, curCnt, curSide});  // 'F'는 카운트하지 않음
                    else
                        pq.add(new int[]{nX, nY, curCnt, curSide + 1});  // 인접 쓰레기 칸 수 증가
                    visit[nX][nY] = true;
                }
                // 빈 칸인 경우
                else
                {
                    pq.add(new int[]{nX, nY, curCnt, curSide});
                    visit[nX][nY] = true;
                }
            }
        }
    }

    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
