package BFS_DFS;
//움직이는 미로 탈출
import java.io.*;
import java.util.*;

public class boj_16954
{
    static char[][] map;
    //상, 하, 좌, 우, 대각, 제자리
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];
        for(int i=0; i<8; i++)
        {
            String str = br.readLine();
            for(int j=0; j<8; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        System.out.print(bfs());
    }
    //시작 = (7,0) 도착 = (0,7)
    static int bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{7,0});
        while (!q.isEmpty())
        {
            //현재 레벨만 탐색, 사용자가 움직이고 벽이 움직임
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];
                //이전 레벨에서 추가한 좌표가 벽을 내리는 동작 이후에 벽이 된 경우 탐색을 하지 않음
                if(map[curX][curY] == '#') continue;
                if(curX == 0 || curY == 7) return 1;
                for(int dir=0; dir<9; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    if(OOB(nX,nY) || map[nX][nY] == '#') continue;
                    q.add(new int[]{nX,nY});
                }
            }
            wallDrop();
        }
        return 0;
    }
    static void wallDrop()
    {
        for(int i=7; i>=0; i--)
        {
            for(int j=0; j<8; j++)
            {
                if(map[i][j] == '#')
                {
                    map[i][j] = '.';
                    if(i != 7)
                    {
                        map[i+1][j] = '#';
                    }
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x<0 || y<0 || x >= 8 || y >= 8;
    }
}
//레벨 탐색(층별 탐색)이 필요한 경우: 큐의 사이즈를 활용하여 각 레벨을 구분해야 함.
//상태 변화가 발생하거나, 특정 시점에 변화가 일어나는 문제: 큐의 사이즈로 현재 레벨을 처리한 후 변화 반영.
//단순 탐색 문제: 큐의 사이즈 없이도 풀 수 있음. 최단 경로 탐색만으로 해결 가능.