package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_11286
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) ->
        {
            if(Math.abs(o1) == Math.abs(o2))
                return Integer.compare(o1,o2);
            return Integer.compare(Math.abs(o1), Math.abs(o2));
        });

        while (n -- > 0)
        {
            int x = Integer.parseInt(br.readLine());

            if(x == 0)
            {
                if(pq.isEmpty())
                {
                    sb.append(0).append('\n');
                }
                else
                    sb.append(pq.poll()).append('\n');
            }
            else
                pq.add(x);
        }

        System.out.print(sb);
    }
}
