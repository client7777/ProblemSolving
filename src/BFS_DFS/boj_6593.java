package BFS_DFS;
//상범 빌딩
import java.io.*;
import java.util.*;

public class boj_6593
{
    static int[] dz = {0,0,0,0,-1,1};
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static char[][][] map;
    static int[][][] dist;
    static int l,r,c;
    static int endZ,endX,endY;

    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if(l == 0 && r == 0 && c == 0) break;
            map = new char[l][r][c];
            dist = new int[l][r][c];
            Queue<int[]> q = new LinkedList<>();
            boolean escaped = false;

            for(int i=0;i<l;i++)
            {
                for(int j=0;j<r;j++)
                {
                    String str = br.readLine();
                    for(int k=0;k<c;k++)
                    {
                        map[i][j][k] = str.charAt(k);
                        dist[i][j][k] = -1;
                        if(map[i][j][k] == 'S')
                        {
                            q.add(new int[]{i,j,k});
                            dist[i][j][k] = 0;
                        }
                        if(map[i][j][k] == 'E')
                        {
                            endZ = i;
                            endX = j;
                            endY = k;
                        }

                    }
                }
                br.readLine();
            }
            while(!q.isEmpty() && !escaped)
            {
                int[] cur = q.poll();
                int curZ = cur[0];
                int curX = cur[1];
                int curY = cur[2];
                for(int dir=0; dir<6; dir++)
                {
                    int nZ = curZ + dz[dir];
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    if(OOB(nZ,nX,nY) || map[nZ][nX][nY] == '#' || dist[nZ][nX][nY] != -1) continue;;
                    q.offer(new int[]{nZ,nX,nY});
                    dist[nZ][nX][nY] = dist[curZ][curX][curY] + 1;
                    if(map[nZ][nX][nY] == 'E')
                    {
                        sb.append("Escaped in ").append(dist[nZ][nX][nY]).append(" minute(s).\n");
                        escaped = true; // 탈출에 성공했으므로 escaped를 true로 설정
                        break;
                    }
                }
            }
            if(!escaped)
            {
                sb.append("Trapped!\n");
            }
        }
        System.out.print(sb.toString());
    }
    static boolean OOB(int z, int x, int y)
    {
        return z < 0 || x < 0 || y < 0 || z >= l || x >= r || y >= c;
    }
}
