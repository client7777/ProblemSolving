package Graph;

import java.io.*;
import java.util.*;

public class boj_1445
{
    static int n,m,startX, startY, endX, endY;
    static char[][] map;
    static boolean [][] visit;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visit=new boolean[n][m];

        for(int i=0;i<n;i++)
        {
            String str = br.readLine();
            for(int j=0; j<m; j++)
            {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'S')
                {
                    startX = i;
                    startY = j;
                }

                if(map[i][j] == 'F')
                {
                    endX = i;
                    endY = j;
                }
            }
        }
        dijkstra();
    }
    private static void dijkstra()
    {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) return o1[3] - o2[3];  // 쓰레기 칸 수, 인접 쓰레기 칸 수로 우선순위 설정
            return o1[2] - o2[2];
        });
        pq.add(new int[]{startX, startY, 0 ,0});

        while(!pq.isEmpty())
        {
            int[] cur = pq.poll();

            int curX = cur[0];
            int curY = cur[1];
            int curCnt = cur[2];
            int curSide = cur[3];

            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];

                if(OOB(nX,nY) || visit[nX][nY]) continue;

                if(nX==endX && nY==endY)
                {
                    System.out.print(curCnt + " " + curSide);
                    return;
                }

                if(map[nX][nY] == 'g')
                {
                    pq.add(new int[]{nX,nY,curCnt + 1, curSide});
                    visit[nX][nY] = true;
                }
                else
                {
                    boolean flag=true;

                    for(int i=0; i<4; i++)
                    {
                        int nnX = nX + dx[i];
                        int nnY = nY + dy[i];

                        if(OOB(nnX,nnY)) continue;

                        if(map[nnX][nnY] == 'g')
                        {
                            pq.add(new int[]{nX,nY,curCnt,curSide+1});
                            flag=false;
                            break;
                        }
                    }
                    if(flag)
                        pq.add(new int[]{nX,nY,curCnt,curSide});

                    visit[nX][nY] = true;
                }
            }
        }
    }

    static boolean OOB(int x,int y)
    {
        return  x < 0 || y < 0 || x >= n || y >= m;
    }
}