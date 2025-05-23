package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_28115
{
    static int n;
    static long[] a;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        a = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            a[i] = Integer.parseInt(st.nextToken());
        }
        //수열의 길이가 1인 경우
        if(n == 1)
        {
            sb.append("YES").append('\n');
            sb.append(a[0] - 1).append('\n');
            sb.append("1");
        }
        else
        {
            long d = a[1] - a[0]; //공차
            boolean isPossible = true;

            for(int i=1; i<n; i++)
            {
                //주어진 수열 a가 등차수열이 아니라면
                if(a[i] - a[i-1] != d)
                {
                    isPossible = false;
                    break;
                }
            }
            //b[i] = a[i] - (i+1)
            //c[i] = i+1
            if(isPossible)
            {
                sb.append("YES").append('\n');
                for(int i=0; i<n; i++)
                {
                    sb.append(a[i] - (i+1)).append(" ");
                }
                sb.append('\n');

                for(int i=0; i<n; i++)
                {
                    sb.append(i+1).append(" ");
                }
            }
            else
                sb.append("NO");
        }
        System.out.print(sb);
    }
}
