import java.io.*;
import java.util.*;

public class Main
{
    static int r,c;
    static char[][] map;
    static int[][] dist;
    static boolean[] visit;
    static int minDist;
    static int INF = Integer.MAX_VALUE;
    static int startX, startY;
    static ArrayList<int[]> dirtList;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            if(r == 0 && c == 0) break; // 0,0을 입력받으면 프로그램 종료

            map = new char[r][c];
            dirtList = new ArrayList<>(); // 매 테스트케이스마다 초기화

            for(int i=0; i<r; i++)
            {
                String str = br.readLine();
                for(int j=0; j<c; j++)
                {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == 'o')
                    {
                        startX = i;
                        startY = j;
                        dirtList.add(0,new int[]{i,j});
                    }
                    if(map[i][j] == '*')
                    {
                        dirtList.add(new int[]{i,j});
                    }
                }
            }
            int size = dirtList.size();
            dist = new int[size][size];
            for(int i=0; i<size; i++)
            {
                Arrays.fill(dist[i], INF);
            }

            boolean reachable = true;
            for(int i=0; i<size; i++)
            {
                if(!bfs(i,size))
                {
                    reachable = false;
                    break;
                }
            }
            if(!reachable)
            {
                sb.append(-1).append('\n');
            }
            else
            {
                minDist = INF;
                visit = new boolean[size];
                visit[0] = true;
                backTrack(0,0,size,0);
                sb.append(minDist).append('\n');
            }
        }
        System.out.print(sb);
    }
    static boolean bfs(int start, int size)
    {
        int[] startPos = dirtList.get(start);
        int startNodeX = startPos[0];
        int startNodeY = startPos[1];
        boolean [][] visit = new boolean[r][c];
        visit[startNodeX][startNodeY] = true;
        Queue<int[]> q= new LinkedList<>();
        q.add(new int[]{startNodeX, startNodeY, 0});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];
            for(int i=0; i<size; i++)
            {
                int[] targetPos = dirtList.get(i);
                if(curX == targetPos[0] && curY == targetPos[1])
                {
                    dist[start][i] = curDist;
                }
            }
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || map[nX][nY] == 'x' || visit[nX][nY]) continue;
                q.add(new int[]{nX,nY,curDist + 1});
                visit[nX][nY] = true;
            }
        }
        for(int i=0; i<size; i++)
        {
            if(dist[start][i] == INF)
                return false;
        }
        return true;
    }
    static void backTrack(int depth, int curPos, int size, int curDist)
    {
        if(depth == size-1)
        {
            minDist = Math.min(minDist, curDist);
            return;
        }
        for(int i=1; i<size; i++)
        {
            if(!visit[i])
            {
                visit[i] = true;
                int nextDist = curDist + dist[curPos][i];
                if(nextDist < INF)
                {
                    backTrack(depth + 1, i, size, nextDist);
                }
                visit[i] = false;
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= r || y >= c;
    }
}
