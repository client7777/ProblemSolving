package zzz;

import java.io.*;
import java.util.*;

public class boj_6426
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = 1;
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int z = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            if (z == 0 && i == 0 && m == 0 && l == 0) break;

            Map<Integer, Integer> seen = new HashMap<>();
            int step = 0;

            while (!seen.containsKey(l))
            {
                seen.put(l, step);
                l = (z * l + i) % m;
                step++;
            }
            int cycle = step - seen.get(l); // 현재 단계 - 처음 나타난 단계
            sb.append("Case ").append(caseNum++).append(": ").append(cycle).append('\n');
        }

        System.out.print(sb);
    }
}
