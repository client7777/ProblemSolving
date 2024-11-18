package zzz;

import java.io.*;
import java.util.StringTokenizer;

public class boj_19944
{
    public static void main(String[] args) throws IOException
    {
        // 뉴비 - 1,2학년
        // 올드비 - n학년 이하면서 뉴비가 아님
        // TLE 뉴비도 올드비도 아님
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if(m == 1 || m == 2)
        {
            System.out.print("NEWBIE!");
        }
        else if(m <= n)
        {
            System.out.print("OLDBIE!");
        }
        else if(m > n)
        {
            System.out.print("TLE!");
        }
    }
}
