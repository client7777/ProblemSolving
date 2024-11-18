package BFS_DFS;
// 모든 역을 직접 연결하는 방식 : m * k * k -> 중복연결이 발생할 수 있음
// 가상의 노드를 설정 : m * k
import java.io.*;
import java.util.*;

public class boj_5214
{
    static int n,m,k;
    static ArrayList<Integer>[] adList;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 역의 수
        k = Integer.parseInt(st.nextToken()); // 연결하는 역의 수
        m = Integer.parseInt(st.nextToken()); // 하이퍼 튜브의 수
        
        adList = new ArrayList[m+n+1]; // 하이퍼튜브도 가상의 노드로 생각함
        for(int i=1; i<=m+n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=1; i<=m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int tube = n+i; // 하이퍼 튜브를 가상의 노드로 추가함
            for(int j=0; j<k; j++)
            {
                int node = Integer.parseInt(st.nextToken());
                adList[node].add(tube);
                adList[tube].add(node);
            }
        }
        System.out.print(bfs(1));
    }
    static int bfs(int start)
    {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visit = new boolean[n+m+1];
        visit[start] = true;
        q.add(new int[]{start, 1});
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDist = cur[1];
            if(curNode == n)
            {
                return curDist/2 + 1; // 임의로 만든 하이퍼튜브 노드로 인해 늘어난 거리를 계산
            }
            for(int next:adList[curNode])
            {
                if(!visit[next])
                {
                    q.add(new int[]{next, curDist + 1});
                    visit[next] = true;
                }
            }
        }
        return -1;
    }
}
