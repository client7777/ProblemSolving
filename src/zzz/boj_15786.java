package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_15786
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++)
        {
            st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();

            int idx = 0;
            for(char ch:str1.toCharArray())
            {
                if(idx < n && ch == str.charAt(idx))
                {
                    idx++;
                }
            }
            if(idx == n)
            {
                sb.append("true").append('\n');
            }
            else
                sb.append("false").append('\n');
        }
        System.out.print(sb);
    }
}
