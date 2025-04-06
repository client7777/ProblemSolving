import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o));
        for(int i=0; i<4; i++)
        {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());

        int sum = 0;
        for(int i=0; i<3; i++)
        {
            sum += pq.poll();
        }

        System.out.print(sum + Math.max(a,b));
    }
}