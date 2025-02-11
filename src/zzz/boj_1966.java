package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1966
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (test_case-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            Queue<int[]> q = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o->-o)); // 중요도는 숫자가 큰게 큼

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++)
            {
                int num = Integer.parseInt(st.nextToken());
                q.add(new int[]{i,num});
                pq.add(num);
            }

            int order = 0;

            while (!q.isEmpty())
            {
                int[] cur = q.poll();
                int curIdx = cur[0];
                int curImport = cur[1];

                if(curImport == pq.peek())
                {
                    order++;
                    pq.poll();

                    if(curIdx == m)
                    {
                        sb.append(order).append('\n');
                        break;
                    }
                }
                else
                    q.add(cur);
            }
        }
        System.out.print(sb);
    }
}
