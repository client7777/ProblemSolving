package BFS_DFS;
// 입력의 범위가 10억이므로 1차원 방문 배열을 사용해서 중복 체크를 한다면 메모리 초과 -> set을 이용해서 중복 방지
import java.io.*;
import java.util.*;

public class boj_14395
{
    static long s,t;
    static HashSet<Long> visit = new HashSet();
    static final long LIMIT = 1_000_000_000L;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Long.parseLong(st.nextToken());
        t = Long.parseLong(st.nextToken());

        System.out.print(bfs());
    }
    static String bfs()
    {
        if(s == t)
        {
            return "0";
        }
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, ""));
        visit.add(s);

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            if(cur.value == t)
            {
                return cur.operation;
            }
            long next;
            next = cur.value * cur.value;
            if(next <= LIMIT && !visit.contains(next))
            {
                q.add(new Node(next, cur.operation + "*"));
                visit.add(next);
            }
            next = cur.value + cur.value;
            if(next <= LIMIT && !visit.contains(next))
            {
                q.add(new Node(next, cur.operation + "+"));
                visit.add(next);
            }
            next = cur.value - cur.value;
            if(next <= LIMIT && !visit.contains(next))
            {
                q.add(new Node(next, cur.operation + "-"));
                visit.add(next);
            }
            if(cur.value != 0)
            {
                next = cur.value / cur.value;
                if(next <= LIMIT && !visit.contains(next))
                {
                    q.add(new Node(next, cur.operation + "/"));
                    visit.add(next);
                }
            }
        }
        return "-1";
    }
    static class Node
    {
        long value;
        String operation;
        Node(long value, String operation)
        {
            this.value = value;
            this.operation = operation;
        }
    }
}
