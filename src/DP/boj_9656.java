package DP;

import java.io.*;
//마지막 돌을 가져가는 사람이 패배
public class boj_9656
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.print(n % 2 == 0 ? "SK" : "CY");
    }
}
// 돌의 개수가 짝수일 때 마지막 돌은 항상 창영이가 가져가므로 짝수인 경우 항상 상근이가 이김
