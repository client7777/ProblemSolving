package Graph;
//이분탐색 + BFS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1939
{
    static int n,m; // 노드, 간선
    static ArrayList<int[]>[] adList; // 인접리스트
    static int l = 0,r; // 최소, 최대 중량
    static boolean[] visit;
    static int start, end;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adList = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            adList[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adList[a].add(new int[]{b,c});
            adList[b].add(new int[]{a,c});
            r = Math.max(r, c); // 중량 제한의 최대 값을 r에 저장
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        System.out.print(binary());
    }
    static int binary()
    {
        int ans = -1;
        while (l<=r)
    {
        visit = new boolean[n+1];

        int mid = (l + r) / 2; // 중간 중량을 구함
        visit[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty())
        {
            int cur = q.poll();
            for(int[] next:adList[cur])
            {
                int nextNode = next[0]; // 다음 섬
                int weight = next[1]; // 다리의 중량
                if(visit[nextNode]) continue;
                if(weight < mid) continue;
                visit[nextNode] = true;
                q.add(nextNode);
            }
        }
        if(visit[end])
        {
            l = mid + 1;
            ans = mid;
        }
        else
        {
            r = mid - 1;
        }
    }
        return  ans;
    }
}
