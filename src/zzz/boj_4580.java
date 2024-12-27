package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_4580
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());

            if(k == 0) break;

            int[] arr = new int[k+1];

            for(int i=1; i<=k; i++)
            {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=k; i++)
            {
                int gap = arr[i] - arr[i-1];

                if(gap == 0) continue;

                for(int j=0; j<gap; j++)
                {
                    sb.append(i).append(" ");
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
