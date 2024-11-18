package BFS_DFS;
// 시간복잡도 = 각 노드에 대해 최대 6번 이동을 시도하므로 O(6*100)
import java.io.*;
import java.util.*;
//주사위 굴리기 -> 반복문을 통해서 좌표에 더해주는 방식으로 구현
//뱀과 사다리를 통한 이동 -> 인접 리스트로 구현
//큐에 시작점 1과 주사위를 굴린 횟수를 초기에 추가하고 큐에서 좌표를 꺼냈을 때 100에 도달하면 주사위를 굴린 횟수를 리턴
public class boj_16928
{
    static int n,m; // 뱀과 사다리의 수
    static ArrayList<Integer>[] adList;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adList = new ArrayList[100+1];
        for(int i=1; i<=100; i++)
        {
            adList[i] = new ArrayList<>();
        }
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adList[x].add(y);
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adList[u].add(v);
        }
        System.out.print(bfs());
    }
    static int bfs()
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,0}); //시작점 1, 최소 주사위를 굴린 횟수
        boolean[] visit = new boolean[101];
        visit[1] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curX = cur[0];
            int curCnt = cur[1];
            if(curX == 100) return curCnt;
            for(int dir=1; dir<=6; dir++)
            {
                int nX = curX + dir;
                //좌표의 범위가 100을 넘기거나 이미 방문한 좌표인 경우 무시
                if(nX > 100 || visit[nX]) continue;
                //nX 좌표에 뱀이나 사다리가 있는 경우
                if(!adList[nX].isEmpty())
                {
                    nX = adList[nX].get(0);
                    q.add(new int[]{nX, curCnt+ 1});
                    visit[nX] = true;
                }
                //nX 좌표에 사다리나 뱀이 없다면 주사위를 굴려서 이동
                else
                {
                    q.add(new int[]{nX, curCnt + 1});
                    visit[nX] = true;
                }

            }
        }
        return -1;
    }
}
