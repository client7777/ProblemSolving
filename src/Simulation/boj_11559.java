package Simulation;
// 더이상 연쇄가 일어나지 않을 때까지 루프를 돌면서 연쇄의 횟수를 계산
// 연쇄 폭탄이 끝나면 중력을 받아 다른 뿌요들이 아래로 떨어지는 연산 -> 다시 연쇄가 일어나는지 탐색하는 연산
import java.io.*;
import java.util.*;
public class boj_11559
{
    static char[][] map = new char[12][6];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<12; i++)
        {
            String str = br.readLine();
            for(int j=0; j<6; j++)
            {
                map[i][j] = str.charAt(j);
            }
        }
        // 맵 생성
        int cnt = 0;
        while(true)
        {
            boolean isChained = puyo(); // 연쇄가 있는지 판별하고 연쇄를 처리하는 함수
            if(!isChained) break; // 연쇄가 없다면 루프 탈출
            gravity(); // 뿌요를 밑으로 떨굼
            cnt++;

        }
        System.out.println(cnt);
    }
    static boolean puyo()
    {
        boolean[][] visit = new boolean[12][6];
        //방문 여부를 관리하는 배열을 함수가 호출될 때마다 재생성함으로 인해 독립적인 탐색을 보장
        boolean isChained = false; // 연쇄가 있는지 판별하는 변수
        for(int i=0; i<12; i++)
        {
            for(int j=0; j<6; j++)
            {
                if(!visit[i][j] && map[i][j] != '.')
                {
                    visit[i][j] = true;
                    if(bfs(i,j,visit))
                    {
                        isChained = true;
                    }
                }
            }
        }

        return isChained;
    }
    static boolean bfs(int x,int y,boolean[][] visit)
    {
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> remove = new ArrayList<>();
        q.add(new int[]{x,y});
        remove.add(new int[]{x,y});
        visit[x][y] = true;
        char color = map[x][y];

        while(!q.isEmpty())
        {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];
                for(int dir=0; dir<4; dir++)
                {
                    int nX = curX + dx[dir];
                    int nY = curY + dy[dir];
                    // 좌표가 범위 밖이거나, 이미 방문했거나, 현재 뿌요와 색깔이 다르다면 넘김
                    if(OOB(nX, nY) || visit[nX][nY] || color != map[nX][nY]) continue;
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                    remove.add(new int[]{nX,nY});
                }
        }
        //상, 하, 좌, 우로 연결된 뿌요가 4개 이상이면
        if(remove.size() >= 4)
        {
            // 연쇄폭발처리하고
            for(int[] val:remove)
            {
                map[val[0]][val[1]] = '.';
            }
            // true값 리턴
            return true;
        }
        else
        {
            return false;
        }
    }
    static void gravity()
    {
        for(int j=0; j<6; j++)
        {
            int empty = 11;
            for(int i=11; i>=0; i--)
            {
                if(map[i][j] != '.')
                {
                    char swap = map[i][j];
                    map[i][j] = '.';
                    map[empty][j] = swap;
                    empty--;
                }
            }
        }
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 ||  y < 0 || x >= 12 || y >= 6;
    }
}
