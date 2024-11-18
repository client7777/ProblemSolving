package BFS_DFS;
// 나이트의 이동
import java.io.*;
import java.util.*;

public class boj_7562
{
    static int[] dx = {-2,-2,2,2,-1,1,-1,1};
    static int[] dy = {-1,1,-1,1,-2,-2,2,2};
    static int l;
    static int[][] map;
    static boolean[][] visit;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int t=0; t<test_case; t++)
        {
            l = Integer.parseInt(br.readLine());
            map = new int[l][l];
            visit = new boolean[l][l];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken()); // 현재 킹의 위치
            
            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken()); // 킹이 이동할 곳

            if(startX == endX && startY == endY)
            {
                sb.append(0).append('\n');
                continue;
            }// 현재 킹의 위치와 이동할 곳의 좌표가 같다면

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{startX,startY}); // 현재 나이트가 있는 칸을 큐에 넣음
            visit[startX][startX] =true;

            while(!q.isEmpty())
            {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];

                for(int dir=0; dir<8; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    //킹이 이동할 수 없는 조건
                    //범위 밖이거나 이미 방문한 곳은 탐색 대상에서 제외
                    if(OOB(nX, nY) || visit[nX][nY]) continue;
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                    map[nX][nY] = map[curX][curY] + 1;

                    if(nX == endX && nY == endY)
                    {
                        sb.append(map[endX][endY]).append('\n');
                        q.clear();;
                        break;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= l || y>= l;
    }

}
