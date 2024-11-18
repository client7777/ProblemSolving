package DP;
// 피보나치 수열
import java.io.*;

public class boj_1003
{
    static StringBuilder sb = new StringBuilder();
    static int[][] fibo = new int[42][2];
    public static void main(String[] args) throws IOException
    {
        fibo[0][0] = 1;
        fibo[1][1] = 1;
        for(int i=2; i<=40; i++)
        {
            fibo[i][0] = fibo[i-1][0] + fibo[i-2][0];
            fibo[i][1] = fibo[i-1][1] + fibo[i-2][1];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        for(int i = 0; i < test_case; i++)
        {
            int n = Integer.parseInt(br.readLine());
            sb.append(fibo[n][0]).append(' ').append(fibo[n][1]).append('\n');
        }
        System.out.print(sb);
    }
}
/*
* 1.테이블 정의하기
* fibo[i][k] = 숫자 i가 k(0 or 1)를 호출한 횟수
* 2.점화식 찾기
* fibo[i][k] = fibo[i-1][k] + fibo[i-2][k]
* 3.초기값 정하기
* fibo[0][0] = 1 fibo[1][1] = 1
* */

/*

import java.io.*;
public class Main
{
    static StringBuilder sb = new StringBuilder();
    static int zero,one;
    static int[] arr_zero, arr_one;
    public static void main(String[] args) throws IOException
    {
        arr_zero = new int[41]; // i가 0을 호출한 횟수
        arr_one  = new int[41]; // i가 1을 호출한 횟수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tets_case = Integer.parseInt(br.readLine());
        arr_zero[0] = 1; arr_one[0] = 0;
        arr_zero[1] = 0; arr_one[1] = 1;
        arr_zero[2] = 1; arr_one[2] = 1;
        //n번째 피보나치 수까지 호출하는 0과 1의 횟수를 모두 미리 구해서 배열에 저장해놓음
        for(int i=3; i<=40; i++)
        {
            arr_zero[i] = arr_zero[i-1] + arr_zero[i-2];
            arr_one[i] = arr_one[i-1] + arr_one[i-2];
        }
        // 반복문을 돌면서 n번째 항을 입력받아 O(1)에 원하는 항에 접근
        for(int i=0; i<tets_case; i++)
        {
            int n = Integer.parseInt(br.readLine());
            sb.append(arr_zero[n]).append(' ').append(arr_one[n]).append('\n');
        }
        System.out.print(sb);
    }
}

 */