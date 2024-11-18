package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_16546
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] visit = new boolean[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n-1; i++)
        {
            visit[Integer.parseInt(st.nextToken())] = true;
        }
        for(int i=1; i<=n; i++)
        {
            if(!visit[i])
            {
                System.out.print(i);
                return;
            }
        }
    }
}
