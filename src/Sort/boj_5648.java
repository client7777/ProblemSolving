package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5648
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        while (n>0)
        {
            while (st.hasMoreTokens())
            {
                arr[--n] = Long.parseLong(new StringBuilder(st.nextToken()).reverse().toString());
            }
            if(n > 0)
                st = new StringTokenizer(br.readLine());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for(long val:arr)
        {
            sb.append(val).append('\n');
        }

        System.out.print(sb);
    }
}
