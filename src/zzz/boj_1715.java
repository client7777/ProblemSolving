package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj_1715
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->o));
        for(int i=0; i<n; i++)
        {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;
        while (pq.size() > 1)
        {
            int num1 = pq.poll();
            int num2 = pq.poll();

            ans += (num1 + num2);

            pq.add(num1 + num2);
        }

        System.out.print(ans);
    }
}
