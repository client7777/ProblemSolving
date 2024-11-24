package Recursion;

import java.io.*;
import java.util.*;

public class boj_1629
{
    public static void main(String[] args) throws IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            long ans = pow(a,b,c);
            System.out.print(ans);
    }
    static long pow(int a, int b, int c)
    {
        //base condition
       if(b==1)
       {
           return a%c;
       }

       long val = pow(a,b/2,c); // b를 절반으로 나눠서 재귀 호출
       val = val * val % c; // 절반 결과를 제곱 후 나머지 계산
       if(b%2==0) return val; // b가 짝수라면 그대로 반환
       return val * a%c; // b가 홀수라면 a 곱한 뒤 나머지 계산
    }
}

//1승을 계산할 수 있다 -> k승을 계산했으면 2k승과 2k+1승도 O(1)에 계산할 수 있다.
//12^58 mod 67 = 4 -> 12^116 mod 67 = 16
