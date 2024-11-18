package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_13222
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int len = w * w + h * h;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
        {
            int cur = Integer.parseInt(br.readLine());
            if(cur * cur <= len) sb.append("YES").append('\n');
            else sb.append("NO").append('\n');
        }
        System.out.print(sb);
    }
}
