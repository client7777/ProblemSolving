package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_30404
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int last = 0;
        int cnt = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            int x = Integer.parseInt(st.nextToken());
            if(i == 0) last = x;

            if(last + k < x)
            {
                cnt++;
                last = x;
            }
        }
        System.out.print(cnt+1);
    }
}
