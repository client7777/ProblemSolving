package DP;

import java.io.*;

public class boj_2011
{
    static final int DIVIDE = 1_000_000;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int size = str.length();

        int[] arr = new int[size+1];
        for(int i=1; i<=size; i++)
        {
            arr[i] = str.charAt(i-1) - '0';
        }

        int[] d = new int[size + 1]; //d[i] = i번째 자리까지 암호를 해석했을 때 나올 수 있는 경우의 수
        d[0] = 1;

        for(int i=1; i<=size; i++)
        {
            if(arr[i] > 0) d[i] = (d[i] + d[i-1]) % DIVIDE; //현재 자리 단독 해석
            int x = arr[i-1] * 10 + arr[i];
            if(x >= 10 && x <= 26) d[i] = (d[i] + d[i-2]) % DIVIDE; //앞의 문자와 합쳐서 새로운 두 자리 숫자를 하나의 문자로 해석
        }
        System.out.print(d[size]);
    }
}

