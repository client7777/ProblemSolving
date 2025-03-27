import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static ArrayList<Integer>[] graph;
    static boolean[] gom;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
        {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }

        int s = Integer.parseInt(br.readLine());
        gom = new boolean[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<s; i++)
        {
            int fan = Integer.parseInt(st.nextToken());
            gom[fan] = true;
        }

        System.out.print(bfs() ? "yes" : "Yes");
    }

    static boolean bfs()
    {
        // 출발 지점에 곰곰이 있다면 만나지 않고 여행 불가능
        if(gom[1]) return false;

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty())
        {
            int cur = q.poll();

            // 곰곰을 만나지 않고 이동할 간선이 없는 상태까지 왔다면 성공
            if(graph[cur].isEmpty()) return true;

            for(int next:graph[cur])
            {
                // 다음 노드가 곰곰이면 무시
                if(gom[next]) continue;

                q.add(next);
            }
        }

        return false;
    }
}
