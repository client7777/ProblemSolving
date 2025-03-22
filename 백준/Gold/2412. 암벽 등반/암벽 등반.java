import java.io.*;
import java.util.*;

public class Main
{
    static int n,t;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static HashSet<String> visit = new HashSet<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.putIfAbsent(y, new ArrayList<>());
            map.get(y).add(x);
        }

        for(ArrayList<Integer> list : map.values())
        {
            Collections.sort(list);
        }

        System.out.print(bfs());
    }

    static int bfs()
    {
        visit.add("0,0");

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,0));

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curDist = cur.dist;

            if(curY == t) return curDist;

            for(int i=curY - 2; i<=curY + 2; i++)
            {
                if(!map.containsKey(i)) continue;

                for(int nextX:map.get(i))
                {
                    if(Math.abs(curX - nextX) > 2) continue;

                    String nextKey = nextX + "," + i;
                    if(visit.contains(nextKey)) continue;

                    visit.add(nextKey);
                    q.add(new Node(nextX, i, curDist + 1));
                }
            }
        }

        return -1;
    }

    static class Node
    {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
