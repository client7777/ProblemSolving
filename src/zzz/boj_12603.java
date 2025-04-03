package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_12603
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=test_case; t++)
        {
            sb.append("Case #" + t + ": ");
            int c = Integer.parseInt(br.readLine());
            int size = Integer.parseInt(br.readLine());

            int[] arr = new int[size+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=size; i++)
            {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=size-1; i++)
            {
                for(int j=i+1; j<=size; j++)
                {
                    if(arr[i] + arr[j] == c)
                    {
                        sb.append(i).append(" ").append(j).append('\n');
                    }
                }
            }
        }

        System.out.print(sb);
    }
}
