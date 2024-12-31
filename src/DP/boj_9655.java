package DP;

import java.io.*;

public class boj_9655
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.print(n % 2 == 0 ? "CY" : "SK");
    }
}
//돌을 몇개를 가져가든 n이 홀수일 때 상근이, 짝수일 때 창영이가 항상 이김
