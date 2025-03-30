package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_13975
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case -- > 0)
        {
            int k = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<k; i++)
            {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long sum = 0;
            while (pq.size() > 1)
            {
                long a = pq.poll();
                long b = pq.poll();

                sum += (a + b);
                pq.add(a + b);
            }

            sb.append(sum).append('\n');
        }

        System.out.print(sb);
    }
}
