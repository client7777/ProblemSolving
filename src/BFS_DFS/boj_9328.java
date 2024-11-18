package BFS_DFS;
//열쇠
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class boj_9328
{
    static int test_case, n, m;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] map;
    static boolean[][] visit;
    static boolean[] key;
    static ArrayList<int[]>[] door;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test_case = Integer.parseInt(br.readLine());
        for(int t=0; t<test_case; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n+2][m+2];
            visit = new boolean[n+2][m+2];
            key = new boolean[26];
            door = new ArrayList[26];
            for(int i=0; i<26; i++)
            {
                door[i] = new ArrayList<>();
            }
            for(int i=0; i<n+2; i++)
            {
                for(int j=0; j<m+2; j++)
                {
                    map[i][j] = '.';
                }
            }
            for(int i=1; i<=n; i++)
            {
                String str = br.readLine();
                for(int j=1; j<=m; j++)
                {
                    map[i][j] = str.charAt(j-1);
                }
            }
            String key_input = br.readLine();
            if(!key_input.equals("0"))
            {
                for(int i=0; i<key_input.length(); i++)
                {
                    int tmp = key_input.charAt(i) - 'a';
                    key[tmp] = true;
                }
            }
            sb.append(bfs()).append('\n');
        }
        System.out.print(sb);
    }
    static int bfs()
    {
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        visit[0][0] = true;
        q.add(new int[]{0,0});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int dir=0; dir<4; dir++)
            {
                int nX = curX + dx[dir];
                int nY = curY + dy[dir];
                if(OOB(nX,nY) || visit[nX][nY] || map[nX][nY] == '*') continue;
                int tmp = map[nX][nY];
                //문을 만난 경우
                if(tmp -'A' >= 0 && tmp -'A' <= 25)
                {
                    //해당 문에대한 열쇠가 있다면
                    if(key[tmp -'A'])
                    {
                        //해당 좌표를 방문처리하고 큐에 추가
                        //문을 빈칸으로 갱신
                        visit[nX][nY] = true;
                        q.add(new int[]{nX,nY});
                        map[nX][nY] = '.';
                    }
                    //해당 문에대한 열쇠가 없다면
                    else
                    {
                        //좌표를 배열에 추가
                        door[tmp-'A'].add(new int[]{nX,nY});
                    }
                }
                //열쇠를 만난 경우
                else if(tmp -'a' >= 0 && tmp-'a' <= 25)
                {
                    //방문처리, 큐에 좌표 추가, 열쇠정보를 배열에 추가
                    q.add(new int[]{nX,nY});
                    visit[nX][nY] = true;
                    key[tmp -'a'] = true;

                    for(int j=0; j<26; j++)
                    {
                        if(!door[j].isEmpty() && key[j])
                        {
                            for(int k=0; k<door[j].size(); k++)
                            {
                                int[] xy = door[j].get(k);
                                int x = xy[0];
                                int y = xy[1];
                                map[x][y] = '.';
                                q.add(new int[]{x,y});
                            }
                        }
                    }
                }
                //문서를 만난 경우
                else if(tmp == '$')
                {
                    cnt++;
                    visit[nX][nY] = true;
                    q.add(new int[]{nX,nY});
                }
                //빈칸을 만난 경우
                else
                {
                    visit[nX][nY] = true;
                    q.add(new int[]{nX,nY});
                }
            }
        }
        return cnt;
    }
    static boolean OOB(int x,int y)
    {
        return x < 0 || y < 0 || x >= n+2 || y >= m+2;
    }
}
