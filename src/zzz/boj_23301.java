package zzz;
// 슬라이딩 윈도우
import java.io.*;
import java.util.StringTokenizer;

public class boj_23301
{
    static int[] satisfaction = new int[1001];
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++)
        {
            int k = Integer.parseInt(br.readLine());
            for(int j=0; j<k; j++)
            {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                satisfaction[start] += 1;
                satisfaction[end] -= 1;
            }
        }

        for(int i=1; i<=1000; i++)
        {
            satisfaction[i] += satisfaction[i-1];
        }

        int max = 0;
        int maxStart = 0;

        int cur = 0;
        for(int i=0; i<t; i++)
        {
            cur += satisfaction[i];
        }

        max = cur;

        for(int i=1; i<=1000-t; i++)
        {
            cur = cur - satisfaction[i-1] + satisfaction[i+t-1];

            if(cur > max)
            {
                max = cur;
                maxStart = i;
            }
        }

        System.out.print(maxStart + " " + (maxStart + t));
    }
}
