import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            int n = Integer.parseInt(br.readLine());
            sb.append(bfs(n)).append('\n');
        }

        System.out.print(sb);
    }

    static String bfs(int num)
    {
        if(num == 1) return "1";
        
        boolean[] visit = new boolean[20001];
        visit[1] = true;

        Deque<Pair> dq = new ArrayDeque<>();
        dq.add(new Pair("1", 1));

        while (!dq.isEmpty())
        {
            Pair cur = dq.poll();
            String curString = cur.str;
            int curNode = cur.node;

            if(curNode == 0) return curString;

            if(curString.length() > 100) return "BRAK";

            for(int i=0; i<2; i++)
            {
                int nextNode = (curNode * 10 + i) % num;

                if(visit[nextNode]) continue;

                visit[nextNode] = true;
                dq.add(new Pair(curString + i, nextNode));
            }
        }
        return "BRAK";
    }

    static class Pair
    {
        String str;
        int node;

        public Pair(String str, int node)
        {
            this.str = str;
            this.node = node;
        }
    }
}