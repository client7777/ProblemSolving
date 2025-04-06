import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Node> problem = new PriorityQueue<>();
        for(int i=0; i<n; i++)
        {
            String[] str = br.readLine().split(" ");
            int deadLine = Integer.parseInt(str[0]);
            int cup = Integer.parseInt(str[1]);

            problem.add(new Node(deadLine, cup));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (!problem.isEmpty())
        {
            Node cur = problem.poll();
            pq.add(cur.cup);

            if(pq.size() > cur.deadLine)
            {
                pq.poll();
            }
        }

        int sum = 0;
        while (!pq.isEmpty())
        {
            sum += pq.poll();
        }

        System.out.print(sum);
    }

    static class Node implements Comparable<Node>
    {
        int deadLine;
        int cup;

        public Node(int deadLine, int cup)
        {
            this.deadLine = deadLine;
            this.cup = cup;
        }

        @Override
        public int compareTo(Node o)
        {
           return Integer.compare(this.deadLine, o.deadLine);
        }
    }
}
