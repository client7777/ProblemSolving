package zzz;

import java.util.*;
import java.io.*;

public class boj_23103
{
    static int a = 0,b = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int dist = 0;
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(i > 0)
            {
                dist += Math.abs(x-a) + Math.abs(y-b);
            }

            a = x;
            b = y;
        }
        System.out.print(dist);
    }
}
