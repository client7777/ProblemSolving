import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    static int n,k;
    static int[] path;
    static boolean[] visit;
    static HashMap<Integer, Integer> idxToState = new HashMap<>();
    static HashMap<Integer, Integer> stateToIdx = new HashMap<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++)
        {
            int state = Integer.parseInt(br.readLine(), 2);

            idxToState.put(i, state);
            stateToIdx.put(state, i);
        }

        bfs();

        StringBuilder sb = new StringBuilder();

        int query = Integer.parseInt(br.readLine());
        for(int i=0; i<query; i++)
        {
            int q = Integer.parseInt(br.readLine());

            if(!visit[q]) sb.append(-1).append('\n');
            else
            {
                Stack<Integer> stack = new Stack<>();

                int cur = q;
                while (cur != -1)
                {
                    stack.add(cur);
                    cur = path[cur];
                }

                while (!stack.isEmpty())
                {
                    sb.append(stack.pop()).append(" ");
                }

                sb.append('\n');
            }
        }

        System.out.print(sb);
    }

    static void bfs()
    {
        path = new int[n+1];
        Arrays.fill(path, -1);
        visit = new boolean[n+1];
        visit[1] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty())
        {
            int cur = q.poll();
            int curState = idxToState.get(cur);

            for(int i=0; i<k; i++)
            {
                int nextState = curState ^ (1 << i);

                if(stateToIdx.containsKey(nextState))
                {
                    int nextIdx = stateToIdx.get(nextState);

                    if(visit[nextIdx]) continue;
                    visit[nextIdx] = true;
                    q.add(nextIdx);
                    path[nextIdx] = cur;
                }
            }
        }
    }
}
