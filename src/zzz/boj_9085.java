package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9085
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (test_case-- > 0)
        {
            int n = Integer.parseInt(br.readLine());

            int sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++)
            {
                sum += Integer.parseInt(st.nextToken());
            }

            sb.append(sum).append('\n');
        }

        System.out.print(sb);
    }
}
