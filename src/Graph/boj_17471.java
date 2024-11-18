package Graph;
//게리맨더링
import java.io.*;
import java.util.*;

public class boj_17471
{
    static int n;
    static int[] pop;
    static ArrayList<Integer>[] adList;
    static boolean[] selected;
    static int minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pop = new int[n + 1];
        selected = new boolean[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=n; i++)
        {
            pop[i] = Integer.parseInt(st.nextToken());
        }

        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }

        for(int i=1; i<=n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            for(int j=0; j<e; j++)
            {
                adList[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        backTrack(1);
        if(minDiff == Integer.MAX_VALUE)
        {
            //두 그룹으로 나눌 수 있는  방법이 없다.
            System.out.print(-1);
        }
        else
        {
            System.out.print(minDiff);
        }
    }
    static void backTrack(int idx)
    {
        //모든 노드를 분류했다면
        if(idx == n + 1)
        {
            //연결성체크
            checkGroup();
            return;
        }

        selected[idx] = true;
        backTrack(idx + 1);

        selected[idx] = false;
        backTrack(idx + 1);
    }
    //연결성 심사
    static void checkGroup()
    {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        for(int i=1; i<=n; i++)
        {
            if(selected[i])
            {
                A.add(i);
            }
            else
            {
                B.add(i);
            }
        }

        //모든 노드가 한쪽 그룹에 쏠린 경우는 무시
        if(A.isEmpty() || B.isEmpty()) return;

        //각 그룹이 모두 연결된 상태라면 -> 인구 계산
        if(isCon(A) && isCon(B))
        {
            int popA = 0;
            int popB = 0;
            for(int node:A)
            {
                popA += pop[node];
            }
            for(int node:B)
            {
                popB += pop[node];
            }
            minDiff = Math.min(minDiff, Math.abs(popA - popB));
        }
    }
    static boolean isCon(List<Integer> group)
    {
        boolean[] visit = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(group.get(0));
        visit[group.get(0)] = true;
        int cnt = 1;
        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int next: adList[cur])
            {
                //다음 노드가 방문하지 않은 노드이고, 현재 그룹에 포함된 노드라면 큐에 추가하고 탐색
                if(!visit[next] && group.contains(next))
                {
                    visit[next] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }
        //탐색을 통해 방문한 노드의 수와 그룹의 사이즈와 같다면 그 그룹은 모두 연결됐다는 의미
        return cnt == group.size();
    }
}
