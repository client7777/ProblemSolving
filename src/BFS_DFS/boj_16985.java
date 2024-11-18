package BFS_DFS;
// 각 판을 회전 -> 각 판을 쌓는 순서 정하기 -> 탐색
import java.io.*;
import java.util.*;

public class boj_16985
{
    static int[][][] map = new int[5][5][5];
    static int[][][] rotatedMap = new int[5][5][5];
    static int ans = Integer.MAX_VALUE;
    static boolean[] isUsed = new boolean[5];
    static int[] order = new int[5];
    static int[] dz = {0,0,0,0,-1,1};
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<5; k++)
                {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        backTrack(0);
        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }
    // 백트래킹 -> 회전수 조합 찾기
    static void backTrack(int depth)
    {
        if(depth == 5)
        {
            //판 쌓는 순서 정하러 가기
            stackLayer(0);
            return;
        }
        for(int i=0; i<4; i++)
        {
            rotate(depth, i);
            backTrack(depth + 1);
            rotate(depth, 4-i);
        }
    }
    static void rotate(int layer, int rotate)
    {
        int[][] origin = map[layer].clone();
        int[][] copyMap = new int[5][5];
        for(int r=0; r<rotate; r++)
        {
            for(int i=0; i<5; i++)
            {
                for(int j=0; j<5; j++)
                {
                    copyMap[j][4-i] = origin[i][j];
                }
            }
            origin = copyMap;
            copyMap = new int[5][5];
        }
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                rotatedMap[layer][i][j] = origin[i][j];
            }
        }
    }
    static void stackLayer(int depth)
    {
        if(depth == 5)
        {
            int dist = bfs();
            if(dist != -1)
            {
                ans = Math.min(ans, dist);
            }
            return;
        }
        for(int i=0; i<5; i++)
        {
            if(!isUsed[i])
            {
                isUsed[i] = true;
                order[depth] = i;
                stackLayer(depth + 1);
                isUsed[i] = false;
            }
        }
    }
    static int bfs()
    {
        int[][][] bfsMap = new int[5][5][5];
        for(int i=0; i< order.length; i++)
        {
            bfsMap[i] = rotatedMap[order[i]].clone(); // 정해진 순서대로 판을 쌓음
        }
        if(bfsMap[0][0][0] == 0 || bfsMap[4][4][4] == 0) return -1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0,0});
        boolean[][][] visit = new boolean[5][5][5];
        visit[0][0][0] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curZ = cur[2];
            int curDist = cur[3];
            if(curX == 4 && curY == 4 && curZ == 4) return curDist;
            for(int dir=0; dir<6; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                int nZ = curZ + dz[dir];
                if(OOB(nX,nY,nZ) || bfsMap[nX][nY][nZ] == 0 || visit[nX][nY][nZ]) continue;
                q.add(new int[]{nX,nY,nZ,curDist + 1});
                visit[nX][nY][nZ] = true;
            }
        }
        return -1;
    }
    static boolean OOB(int x,int y,int z)
    {
        return x < 0 || y < 0 || z < 0 || x >= 5 || y >= 5 || z >= 5;
    }
}