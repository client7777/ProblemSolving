import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main
{
    static int n;
    static Room[] rooms;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true)
        {
            n = Integer.parseInt(br.readLine());

            if(n == 0) break;

            rooms = new Room[n+1];

            graph = new ArrayList[n+1];
            for(int i=1; i<=n; i++)
            {
                graph[i] = new ArrayList<>();
            }

            StringTokenizer st;
            for(int i=1; i<=n; i++)
            {
                st = new StringTokenizer(br.readLine());

                String room = st.nextToken();
                int cost = Integer.parseInt(st.nextToken());

                rooms[i] = new Room(room, cost);

               while (true)
               {
                   int node = Integer.parseInt(st.nextToken());

                   if(node == 0) break;

                   graph[i].add(node);
               }

            }

            sb.append(bfs() ? "Yes" : "No").append('\n');
        }
        
        System.out.print(sb);
    }

    static boolean bfs()
    {
        boolean[] visit = new boolean[n+1];
        visit[1] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1,0) );

        while (!q.isEmpty())
        {
            Node cur = q.poll();
            int curNode = cur.node;
            int curCost = cur.cost;

            if(curNode == n) return true;

            for(int next : graph[curNode])
            {
                String roomType = rooms[next].room;
                int roomCost = rooms[next].cost;

                switch (roomType)
                {
                    case "L":
                        if(!visit[next])
                        {
                            if(curCost < roomCost) curCost = roomCost;
                            visit[next] = true;
                            q.add(new Node(next, curCost));
                        }
                        break;

                    case "T":
                        if(!visit[next] && curCost >= roomCost)
                        {
                            visit[next] = true;
                            q.add(new Node(next, curCost -roomCost));
                        }
                        break;

                    case "E":
                        if(!visit[next])
                        {
                            visit[next] = true;
                            q.add(new Node(next, curCost));
                        }
                        break;
                }
            }
        }

        return false;
    }

    static class Node
    {
        int node;
        int cost;

        public Node(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }
    }

    static class Room
    {
        String room;
        int cost;

        public Room(String room, int cost)
        {
            this.room = room;
            this.cost = cost;
        }
    }
}
