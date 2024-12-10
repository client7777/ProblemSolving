package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_30802
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        int[] arr = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<6; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i=0; i<6; i++)
        {
            cnt += arr[i]/t;
            if(arr[i]%t != 0) cnt++;
        }
        sb.append(cnt).append('\n');
        sb.append(n / p).append(" ").append(n % p);
        System.out.print(sb);
    }
}
