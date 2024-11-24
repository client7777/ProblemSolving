package zzz;

import java.io.*;

public class boj_15727
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.print((n/5) + ((n%5 == 0) ? 0 : 1));
    }
}
// 주어진 거리가 5의 배수인지 아닌지 검사 -> 5의 배수가 아니라면 나머지가 존재할 것이고 한번더 이동해야 함