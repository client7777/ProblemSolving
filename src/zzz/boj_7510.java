package zzz;

import java.io.*;
import java.util.*;

public class boj_7510
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=test_case; t++)
        {
            long[] arr = new long[3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<3; i++)
            {
                arr[i] = Long.parseLong(st.nextToken());
            }
            Arrays.sort(arr);

            sb.append("Scenario #" + t + ":").append('\n');

            sb.append(check(arr[0],arr[1],arr[2]) ? "yes" : "no").append('\n');

            sb.append('\n');
        }
        System.out.print(sb);
    }
    static boolean check(long a, long b, long c)
    {
        long val1 = a * a + b * b;
        long val2 = c * c;

        return val1 == val2;
    }
}
