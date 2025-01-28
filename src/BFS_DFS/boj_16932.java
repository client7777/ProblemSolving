package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16932
{
    static int n,m;
    static int[][] map, group;
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        group = new int[n][m];
        map = new int[n][m];

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 1 && group[i][j] == 0)
                {
                    bfs(i,j,num++);
                }
            }
        }

        int max = 0;

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(map[i][j] == 0)
                {
                    int tmp = 1;

                    HashSet<Integer> set = new HashSet<>();

                    for(int dir=0; dir<4; dir++)
                    {
                        int nX = i + dx[dir];
                        int nY = j + dy[dir];

                        if(OOB(nX,nY)) continue;
                        if(map[nX][nY] == 1 && !set.contains(group[nX][nY]))
                        {
                            set.add(group[nX][nY]);
                            tmp += hashMap.get(group[nX][nY]);
                        }
                    }
                    max = Math.max(max, tmp);
                }
            }
        }
        System.out.print(max);
    }
    static void bfs(int x,int y,int num)
    {
        group[x][y] = num;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        int size = 1;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || map[nX][nY] == 0 || group[nX][nY] != 0) continue;

                q.add(new int[]{nX,nY});
                group[nX][nY] = num;
                size++;
            }
        }
        hashMap.put(num, size);
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n || y >= m;
    }
}
