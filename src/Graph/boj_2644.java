package Graph;

import java.io.*;
import java.util.*;
//노드와 노드까지의 거리
//가중치 = 1
public class boj_2644
{
    static int n; // 노드의 수
    static int v1,v2; // 출발 노드와 목적지 노드
    static List<Integer>[] adList;
    static boolean[] visit;
    static boolean found = false; // 목적지에 도달했는지 여부 체크
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 노드의 개수

        visit = new boolean[n+1];
        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int line = Integer.parseInt(br.readLine());

        for(int i=0; i<line; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adList[x].add(y);
            adList[y].add(x);
        }
        int ans = bfs(v1);
        System.out.print(ans);
    }
    static int bfs(int start)
    {
        Queue<int[]> q = new LinkedList<>();
        visit[start] = true;
        q.add(new int[]{start, 0});

        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(curNode == v2)
            {
                //현재 노드가 목적지 노드와 동일하다면 누적 거리를 반환
                return curDist;
            }
            for(int next:adList[curNode])
            {
                if(!visit[next])
                {
                    visit[next] = true;
                    q.add(new int[]{next, curDist + 1});
                }
            }
        }
        return -1;
    }
}
/*
  static void dfs(int start)
    {
        if(start == v2)
        {
            System.out.print(dist);
            found = true;
            return;
        }

        visit[start] = true;
        for(int next:adList[start])
        {
            if(!visit[next])
            {
                dist++;
                dfs(next);

                //목적지에 도달했으면 탐색 중지
                if(found) return;

                dist--;
            }
        }
    }
*/
