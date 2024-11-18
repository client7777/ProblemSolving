package Greedy;

import java.io.*;
import java.util.*;

public class boj_8980
{
    static int n,c,m;
    static ArrayList<int[]> delivery = new ArrayList<>();
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 출발지
            int destination = Integer.parseInt(st.nextToken()); // 목적지
            int box = Integer.parseInt(st.nextToken());
            delivery.add(new int[]{start, destination, box});
        }
        int[] town = new int[n];
        Arrays.fill(town, c);
        Collections.sort(delivery, new Comparator<int[]>()
        {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                if(o1[1] == o2[1])
                {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        int ans = 0;
        for(int[] val:delivery)
        {
            int start = val[0];
            int end = val[1];
            int box = val[2];
            
            int cur = c;
            for(int i=start; i<end; i++)
            {
                cur = Math.min(cur, town[i]);
            }
            int loadBox = Math.min(cur, box); // 적재할 박스 무게 계산

            for(int i=start; i<end; i++)
            {
                town[i] -= loadBox; // 각 마을의 남은 용량에서 적재한 박스 수를 차감
            }

            ans += loadBox;
        }
        System.out.print(ans);
    }
}
