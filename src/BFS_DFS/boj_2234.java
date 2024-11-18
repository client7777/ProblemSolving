package BFS_DFS;

import java.io.*;
import java.util.*;

public class boj_2234
{
     static int n,m,brokenMax,maxRoom;
     static int[][] arr,visited;
     static Map<Integer,Integer> map = new HashMap<>(); // 방번호 : 방사이즈
     static int[] dx = {0,-1,0,1};
     static int[] dy = {-1,0,1,0};
     static Queue<int[]> q;
    public static void main(String[] args) throws IOException
    {   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        brokenBfs();
        StringBuilder sb = new StringBuilder();
        sb.append(map.size()).append('\n');
        sb.append(maxRoom).append('\n');
        sb.append(brokenMax).append('\n');
        System.out.print(sb);
    }

    public static void bfs()
    {
        q = new LinkedList<>();
        int roomSize = 0;
        int roomNum = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(visited[i][j] == 0)
                {
                    roomSize = 1;
                    visited[i][j] = ++roomNum;
                    q.add(new int[]{i,j});
                    while (!q.isEmpty())
                    {
                        int[] cur = q.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        for(int dir=0; dir<4; dir++)
                        {
                            int nX = curX + dx[dir];
                            int nY = curY + dy[dir];
                            if(OOB(nX,nY)) continue;
                            if((arr[curX][curY] & (1 << dir)) == 0 && visited[nX][nY] == 0)
                            {
                                visited[nX][nY] = visited[curX][curY];
                                roomSize++;
                                q.add(new int[]{nX,nY});
                            }
                        }
                    }
                    map.put(roomNum, roomSize);
                    maxRoom = Math.max(maxRoom, roomSize);
                }
            }
        }
    }
    public static void brokenBfs()
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                int roomNum = visited[i][j];
                for(int dir=0; dir<4; dir++)
                {
                    int nX = i + dx[dir];
                    int nY = j + dy[dir];
                    if(OOB(nX,nY)) continue;
                    if(visited[nX][nY] != roomNum)
                    {
                        brokenMax = Math.max(brokenMax, map.get(visited[nX][nY]) + map.get(roomNum));
                    }
                }
            }
        }
    }
    static boolean OOB(int x, int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
