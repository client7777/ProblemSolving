package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_9699
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=test_case; t++)
        {
            int max = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens())
            {
                max = Math.max(max, Integer.parseInt(st.nextToken()));
            }
            sb.append("Case #" + t + ": " + max).append('\n');
        }
        System.out.print(sb);
    }
}
