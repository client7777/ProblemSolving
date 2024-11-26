package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_13458
{
    static int[] arr;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st  = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken()); //총감독관
        int c = Integer.parseInt(st.nextToken()); //부감독관

        long ans = n; // n개의 시험장이 있으므로 n명의 감독관은 확정
        for(int i=0; i<n; i++)
        {
            int cur = arr[i]-b;
            if(cur<=0) continue;
            ans += cur/c + (cur%c==0 ? 0 : 1);
        }
        System.out.print(ans);
    }
}
