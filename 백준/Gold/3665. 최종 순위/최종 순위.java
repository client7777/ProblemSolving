import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int team;
    static ArrayList<Integer>[] adjList;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        for(int t=0; t<test_case; t++)
        {
            team = Integer.parseInt(br.readLine());

           inDegree = new int[team + 1];

            int[] rank = new int[team + 1];

            adjList = new ArrayList[team + 1];

            for(int i=1; i<=team; i++)
            {
                adjList[i] = new ArrayList<>();
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=team; i++)
            {
                rank[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=team; i++)
            {
                for(int j=i+1; j<=team; j++)
                {
                    adjList[rank[i]].add(rank[j]);
                    inDegree[rank[j]]++;
                }
            }

            int m = Integer.parseInt(br.readLine());

            for(int i=0; i<m; i++)
            {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(adjList[a].contains(b))
                {
                    adjList[a].remove(Integer.valueOf(b));
                    adjList[b].add(a);
                    inDegree[b]--;
                    inDegree[a]++;
                }
                else
                {
                    adjList[b].remove(Integer.valueOf(a));
                    adjList[a].add(b);
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }

            topologicalSort();
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static void topologicalSort()
    {
        Queue<Integer> q = new LinkedList<>();

        ArrayList<Integer> result = new ArrayList<>();

        for(int i=1; i<=team; i++)
        {
            if(inDegree[i] == 0)
            {
                q.add(i);
            }
        }

        boolean flag = false;

        while (!q.isEmpty())
        {
            if(q.size() > 1) // 큐에 2개 이상의 팀이 들어와 있다는 의미는 확실한 순위를 찾을 수 없다는 의미
            {
                flag = true;
                break;
            }

            int cur = q.poll();
            result.add(cur);

            for(int next:adjList[cur])
            {
                inDegree[next]--;
                if(inDegree[next] == 0)
                    q.add(next);
            }
        }

        if(flag)
        {
            sb.append("?").append(" ");
        }
        // 사이클이 발생한 경우
        // 사이클 내의 노드들은 진입차수가 0이 되는 시점이 없어서 결과적으로 result.size()가 전체 노드의 크기보다 작아지는 경우가 발생
        else if(result.size() != team)
        {
            sb.append("IMPOSSIBLE").append(" ");
        }
        else
        {
            for(int val:result)
            {
                sb.append(val).append(" ");
            }
        }
    }
}

