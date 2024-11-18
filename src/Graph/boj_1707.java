package Graph;
//이분 그래프 시간 복잡도 = O(K * (V + E))
//BFS: 각 노드를 한 번씩 방문:O(V), 각 간선을 한 번씩 방문 O(E) 테스트케이스 k 번 반복하므로 전체 시간 복잡도는 위와 같음
import java.util.*;
import java.io.*;

public class boj_1707
{
    static int k, v ,e; // 테스트 케이스, 정점, 간선
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] adList;
    static int[] color;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); // testCase

        for(int t=0; t<k; t++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            adList = new ArrayList[v+1];
            color = new int[v+1];

            for(int i=1; i<=v; i++)
            {
                adList[i] = new ArrayList<>();
            }

            for(int i=0; i<e; i++)
            {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                adList[x].add(y);
                adList[y].add(x);
            }

            boolean isBipartite = true;
            for(int i=1; i<=v; i++)
            {
                if(color[i] == 0)
                {
                    if(!bfs(i))
                    {
                        isBipartite = false;
                        break;
                    }
                }
            }
            if(isBipartite)
            {
                sb.append("YES").append('\n');
            }
            else
            {
                sb.append("NO").append('\n');
            }
        }
        System.out.print(sb);
    }

    static boolean bfs(int start)
    {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            //정정만 있고 간선이 없는 경우 인접 리스트에 아무것도 없어서 바로 빠져나옴
            for(int next:adList[cur])
            {
                //인접한 노드이면서 색깔이 정해지지 않은 노드는
                if(color[next] == 0)
                {
                    // 반대 색으로 칠하고 큐에 좌표 추가
                    color[next] = color[cur] * -1;
                    q.add(next);
                }
                else if(color[next] == color[cur])
                {
                    return false;
                }
            }
        }
        return true;
    }
}
