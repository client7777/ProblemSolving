package zzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9786
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int gcd = gcd(a,b);
            //a,b를 최대 공약수로 나눠주면 기약분수가 됨
            sb.append(a / gcd).append(" ").append(b / gcd).append('\n');
        }
        System.out.print(sb);
    }
    //유클리드 호제법, 2개의 자연수의 최대 공약수를 구함
    static int gcd(int a, int b)
    {
        while (b != 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}
