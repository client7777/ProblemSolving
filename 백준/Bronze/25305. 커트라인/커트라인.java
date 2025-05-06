import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o));

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<k-1; i++) pq.poll();

        System.out.print(pq.poll());
    }
}