package Recursion;
//함수의 정의
//base condition
//재귀 식
import java.util.*;
import java.io.*;

public class boj_1074
{
    public static void main(String[] args)throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.print(Z(n,r,c));

    }
    static int Z(int n, int r, int c)
    {
        if(n == 0)
        {
            //배열의 크기가 가장 작을 때, 배열의 크기가 1*1일 때
            return 0;
        }
        // 2의 n-1제곱 즉, 한변 길이의 절반
        int half = 1<<(n-1);
        //(r,c)가 1번 사각형일 때
        if(r < half && c < half) return Z(n-1, r, c);
        //(r,c)가 2번 사각형일 때
        if(r < half && c >= half) return half * half + Z(n-1,r,c-half);
        //(r,c)가 3번 사각형일 때
        if(r >= half && c<half) return 2*(half * half) + Z(n-1,r-half,c);
        //(r,c)가 4번 사각형일 때
        return 3*(half * half) + Z(n-1,r-half,c-half);
    }
}

