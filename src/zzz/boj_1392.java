package zzz;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1392
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=1; i<=n; i++)
        {
            int time = Integer.parseInt(br.readLine());
            for(int j=0; j<time; j++)
            {
                ans.add(i);
            }
        }
        for(int i=0; i<q; i++)
        {
            int num = Integer.parseInt(br.readLine());
            sb.append(ans.get(num)).append('\n');
        }
        System.out.print(sb);
    }
}
