package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11723
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int s = 0;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            int num;

            switch (command)
            {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    s |= (1 << (num-1));
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    s = s & ~(1 << (num-1));
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    sb.append((s & (1 << (num-1))) != 0 ? 1 : 0).append('\n');
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    s ^= (1 << (num-1));
                    break;
                case "all":
                    s |= (~0);
                    break;
                case "empty":
                    s &= 0;
                    break;
            }
        }
        System.out.print(sb);
    }
}

