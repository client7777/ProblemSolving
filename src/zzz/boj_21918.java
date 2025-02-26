package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_21918
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
        {
            //1 -> 전구가 켜짐 0 -> 전구가 꺼짐
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch (a)
            {
                case 1:
                    arr[b] = c;
                    break;
                case 2:
                    for(int j=b; j<=c; j++)
                    {
                        if(arr[j] == 0) arr[j] = 1;
                        else if(arr[j] == 1) arr[j] = 0;
                    }
                    break;
                case 3:
                    for(int j=b; j<=c; j++)
                    {
                        arr[j] = 0;
                    }
                    break;
                case 4:
                    for(int j=b; j<=c; j++)
                    {
                        arr[j] = 1;
                    }
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++)
        {
            sb.append(arr[i]).append(" ");
        }

        System.out.print(sb);
    }
}
