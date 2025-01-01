package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_27110
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            cnt = cnt + (Math.min(num, n));
        }
        System.out.print(cnt);
    }
}
