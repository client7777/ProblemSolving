package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_31067
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for(int i=0; i<n-1; i++)
        {
            if(arr[i] >= arr[i+1])
            {
                arr[i+1] += k;
                cnt++;
            }
        }
        for(int i=0; i<n-1; i++)
        {
            if(arr[i] >= arr[i+1])
            {
                System.out.print(-1);
                return;
            }
        }
        System.out.print(cnt);
    }
}
