package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_7581
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(l == 0 && w == 0 && h == 0 && v == 0) break;

            if(l == 0)
            {
                l = v / (w * h);
            }
            else if(w == 0)
            {
                w = v / (l * h);
            }
            else if(h == 0)
            {
                h = v / (l * w);
            }
            else if(v == 0)
            {
                v = l * w * h;
            }
            sb.append(l).append(" ").append(w).append(" ").append(h).append(" ").append(v).append('\n');
        }
        System.out.print(sb);
    }
}
