package BFS_DFS;

import java.io.*;
import java.util.*;
public class boj_16946
{
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr;
    static int[][] ans;
    static int[][] group;
    static int[] groupsize;
    static int groupId = 1;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        ans = new int[n][m];
        group = new int[n][m];
        groupsize = new int[n * m + 1];
        for (int i = 0; i < n; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < m; j++)
            {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        //빈칸을 그룹화하고 넘버링
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(arr[i][j]==0 && group[i][j]==0)
                {
                    int size = bfs(i,j);
                    groupsize[groupId++] = size;
                }
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(arr[i][j] == 1)
                {
                    int tot = 1;
                    boolean[] visited = new boolean[groupId];
                    for(int dir=0; dir<4; dir++)
                    {
                        int nX = i + dx[dir];
                        int nY = j + dy[dir];
                        if(!OOB(nX,nY))
                        {
                            int visitedgroupId = group[nX][nY];
                            if(visitedgroupId > 0 && !visited[visitedgroupId])
                            {
                                visited[visitedgroupId] = true;
                                tot += groupsize[visitedgroupId];
                            }
                        }
                    }
                    ans[i][j] = tot%10;
                }
            }
        }
        //정답 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(arr[i][j] == 1)
                {
                    sb.append(ans[i][j]);
                }
                else
                {
                    sb.append(0);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int bfs(int x,int y)
    {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        group[x][y] = groupId;
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(!OOB(nX,nY) && arr[nX][nY] == 0 && group[nX][nY]==0)
                {
                    group[nX][nY] = groupId;
                    q.offer(new int[]{nX,nY});
                    size++;
                }
            }
        }
        return size;
    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y<0 || x>=n || y>=m;
    }
}
